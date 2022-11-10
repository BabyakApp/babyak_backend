package Babyak.babyak_backend.chat.controller;

import Babyak.babyak_backend.chat.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/chat/enter")
    public void enter(ChatDto message) {
        message.setMessage(message.getWriter() + "님이 채팅방에 참여했습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getChatRoomId(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatDto message) {
        template.convertAndSend("/sub/chat/room" + message.getChatRoomId(), message);
    }

}
