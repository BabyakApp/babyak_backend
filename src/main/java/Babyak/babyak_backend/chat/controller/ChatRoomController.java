package Babyak.babyak_backend.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//RestController
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Slf4j
public class ChatRoomController {

//    private final SimpMessagingTemplate template;
//
//    @MessageMapping
//    public void sendMessage(ChatMessageDto chatDto, SimpMessageHeaderAccessor accessor) {
//        template.convertAndSend("/sub/chat/" + chatDto.getChatRoomId(), chatDto);
//    }
//
//    /* 채팅방 목록 조회 */
//    @GetMapping("/rooms")
//    public String getChatRoomList() {
//        return "rooms";
//    }
//
//    /* 채팅방 생성 */
//    @PostMapping(value = "/room")
//    public String createChatRoom(@RequestParam String name, RedirectAttributes redirectAttributes) {
//        log.info("# Create Chat Room, name : [{}]", name);
//        redirectAttributes.addFlashAttribute("roomName");
//        return "redirect:/rooms";
//    }
//
//    /* 채팅방 조회 */
//    @GetMapping("/room")
//    public String getChatRoom(String chatRoomId, String nickname, Model model) {
//        model.addAttribute("chatRoomId", chatRoomId);
//        model.addAttribute("nickname", nickname);
//        return "room";
//    }
}
