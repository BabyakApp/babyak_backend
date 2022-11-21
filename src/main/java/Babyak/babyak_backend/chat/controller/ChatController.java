package Babyak.babyak_backend.chat.controller;

import Babyak.babyak_backend.chat.dto.ChatRoomDto;
import Babyak.babyak_backend.chat.entity.ChatRoom;
import Babyak.babyak_backend.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    /* 채팅방 생성 및 조회는 Rest API로 구현 */

    private final ChatService chatService;

    @PostMapping
    public ChatRoomDto createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoomDto> findAllRoom() {
        return chatService.findAllRoom();
    }

}
