package Babyak.babyak_backend.chat.controller;

import Babyak.babyak_backend.chat.dto.ChatMessageDto;
import Babyak.babyak_backend.chat.dto.ChatRoomDto;
import Babyak.babyak_backend.chat.entity.ChatRoom;
import Babyak.babyak_backend.chat.repository.ChatRoomRepository;
import Babyak.babyak_backend.chat.repository.ChattingRoomRepository;
import Babyak.babyak_backend.chat.service.ChatService;
import Babyak.babyak_backend.common.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {

    /*
     @MessageMapping: Websocket으로 들어오는 메세지 발행(publish) 처리
     client에서 prefix 붙여서 '/pub/chat/message'로 발행 요청하면 controller가 해당 메세지 받아서 처리
     메세지가 발행되면 '/sub/chat/room/{roomId}'로 메세지를 send
     -> client에서는 해당 주소('/sub/chat/room/{roomId}')를 구독(subscribe)하고 있다가 메세지가 전달되면 화면에 출력
     -> 이 때 '/sub/chat/room/{roomId}': 채팅방 구분하는 값이므로 pub/sub에서의 Topic 역할
     또한, 기존의 WebSocketChatHandler가 했던 역할 대체하므로 handler는 삭제
     ------
     subscriber 부분은 서버단에서는 따로 구현할 부분X.
     웹뷰에서 stomp 라이브러리 이용해 subscriber 주소 바라보고 있는 코드만 작성하면 된다
     */

    private final RedisPublisher redisPublisher;
    private final ChattingRoomRepository chattingRoomRepository;
    private final SimpMessageSendingOperations sendingOperations;

    /**
     * websocket "/pub/chat/message" 로 들어오는 메세징을 처리한다.
     */

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        if (ChatMessageDto.MessageType.ENTER.equals(message.getType())) {
            chattingRoomRepository.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        // Websocket에 발행된 메세지를 Redis로 발행한다(publish)
        //sendingOperations.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        redisPublisher.publish(chattingRoomRepository.getTopic(message.getRoomId()),message);
    }

    /* 채팅방 생성 및 조회는 Rest API로 구현

    private final ChatService chatService;

    @PostMapping
    public ChatRoomDto createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoomDto> findAllRoom() {
        return chatService.findAllRoom();
    }

    */
}
