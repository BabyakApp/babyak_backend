package Babyak.babyak_backend.common.component;

import Babyak.babyak_backend.chat.dto.ChatMessageDto;
import Babyak.babyak_backend.chat.repository.ChatRoomRepository;
import Babyak.babyak_backend.chat.repository.ChattingRoomRepository;
import Babyak.babyak_backend.chat.service.ChatService;
import Babyak.babyak_backend.jwt.component.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class StompHandler implements ChannelInterceptor {

    private final ChattingRoomRepository chattingRoomRepository;
    private final ChatService chatService;
    private final JwtTokenProvider jwtTokenProvider;

    // websocket을 통해 들어온 요청이 처리되기 전에 실행됨
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        // websocket 연결 요청: websocket 연결 시 헤더의 jwt 검증
        if (StompCommand.CONNECT == accessor.getCommand()) {
            String jwtToken = accessor.getFirstNativeHeader("token");
            log.info("CONNECT {}", jwtToken);
            // 헤더의 jwt 검증
            jwtTokenProvider.validateToken(jwtToken);
        }
        // 채팅방 subscribe 요청
        else if (StompCommand.SUBSCRIBE == accessor.getCommand()) {
            // 헤더에서 구독할 채팅방의 destination 정보 얻고, roomId 추출
            String roomId = chatService.getRoomId(Optional.ofNullable((String) message
                    .getHeaders()
                    .get("simpDestination"))
                    .orElse("InvalidRoomId"));
            // 채팅방에 들어온 클라이언트 sessionId를 roomId와 맵핑 (나중에 특정 세션이 어떤 채팅방에 들어가 있는지 알기 위함)
            String sessionId = (String) message.getHeaders().get("simpSessionId");
            chattingRoomRepository.setUserEnterInfo(sessionId, roomId);

            // 채팅방 인원수 +1
            chattingRoomRepository.plusUserCount(roomId);

            // 클라이언트 입장 메세지를 채팅방에 발송 (-> redis publish)
            String name = Optional.ofNullable((Principal) message
                    .getHeaders()
                    .get("simpUser"))
                    .map(Principal::getName)
                    .orElse("UnknownUser");
            chatService.sendChatMessage(ChatMessageDto.builder()
                    .type(ChatMessageDto.MessageType.ENTER)
                    .roomId(roomId)
                    .sender(name)
                    .build());
            log.info("SUBSCRIBED {}, {}", name, roomId);
        }
        // websocket 연결 종료
        else if (StompCommand.DISCONNECT == accessor.getCommand()) {
            // 연결이 종료된 클라이언트의 sessionId로 채팅방 id를 얻음
            String sessionId = (String) message.getHeaders().get("simpSessionId");
            String roomId = chattingRoomRepository.getUserEnterRoomId(sessionId);

            // 채팅방 인원수 -1
            chattingRoomRepository.minusUserCount(roomId);

            // 클라이언트 퇴장 메세지를 채팅방에 발송 (-> redis publish)
            String name = Optional.ofNullable((Principal) message
                    .getHeaders()
                    .get("simpUser"))
                    .map(Principal::getName)
                    .orElse("UnknownUser");
            chatService.sendChatMessage(ChatMessageDto.builder()
                    .type(ChatMessageDto.MessageType.QUIT)
                    .roomId(roomId)
                    .sender(name)
                    .build());

            // 퇴장한 클라이언트의 roomId 맵핑 정보 삭제
            chattingRoomRepository.removeUserEnterInfo(sessionId);
            log.info("DISCONNECTED {}, {}", sessionId, roomId);
        }

        return message;
    }


}
