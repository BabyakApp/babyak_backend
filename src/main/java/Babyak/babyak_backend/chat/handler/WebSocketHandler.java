package Babyak.babyak_backend.chat.handler;

import Babyak.babyak_backend.chat.dto.ChatMessageDto;
import Babyak.babyak_backend.chat.dto.ChatRoomDto;
import Babyak.babyak_backend.chat.entity.ChatRoom;
import Babyak.babyak_backend.chat.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
//        TextMessage textMessage = new TextMessage("Welcome Chatting Server! :)");
//        session.sendMessage(textMessage);

        // WebSocket client로부터 채팅 메세지 전달받아 채팅 메세지 객체로 전환
        ChatMessageDto chatMessage = objectMapper.readValue(payload, ChatMessageDto.class);
        // 전달받은 메세지에 담긴 채팅방 id로 발송 대상인 채팅방 정보 조회
        ChatRoomDto chatRoom = chatService.findRoomById(chatMessage.getRoomId());
        // 해당 채팅방에 입장해있는 모든 client들 (WebSocket session)에게 타입에 따른 메세지 발송
        chatRoom.handleActions(session, chatMessage, chatService);
    }
}
