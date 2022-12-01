package Babyak.babyak_backend.chat.controller;

import Babyak.babyak_backend.chat.dto.ChatRoomDto;
import Babyak.babyak_backend.chat.repository.ChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//RestController
@Controller
@RequiredArgsConstructor
@RequestMapping( "/chat")
public class ChatRoomController {

    /**
     * Websocket 통신 외에 채팅 화면 View 구성을 위해 필요한 Controller 생성
     */

    private final ChattingRoomRepository chattingRoomRepository;

    /* 채팅 리스트 화면 */
    @GetMapping("/room")
    public String rooms (Model model) {
        return "/chat/room";
    }

    /* 모든 채팅방 목록 반환 */
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoomDto> room() {
        return chattingRoomRepository.findAllRoom();
    }

    /* 채팅방 생성 */
    @PostMapping("/room")
    @ResponseBody
    public ChatRoomDto createRoom(@RequestParam String name) {
        return chattingRoomRepository.createChatRoom(name);
    }

    /* 채팅방 입장 화면 */
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    /* 특정 채팅방 조회 */
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomDto roomInfo(@PathVariable String roomId) {
        return chattingRoomRepository.findRoomById(roomId);
    }

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
