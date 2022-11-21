package Babyak.babyak_backend.chat.controller;

import Babyak.babyak_backend.chat.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebSocketController {

//
//    private final SimpMessagingTemplate template;
//
//    // @MessageMapping: 메세지의 destination이 "/chat"이면 sendMessage() method 호출
//    @MessageMapping(value = "/chat")
//    public void sendMessage (ChatDto chatDto, SimpMessageHeaderAccessor accessor) {
//        // SimpMessagingTemplate: 특정 브로커로 메세지 전달 -> 외부 브로커 없으므로 인메모리에 정보 저장
//        // "/sub/chat/{chatRoomId}" 채널을 구독 중인 클라이언트에게 메세지 전송
//        // 메세지의 payload는 chatDto로 들어자
//        template.convertAndSend("/sub/chat/" + chatDto.getChatRoomId(), chatDto);
//    }
//

}
