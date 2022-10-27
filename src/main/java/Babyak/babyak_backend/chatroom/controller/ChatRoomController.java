package Babyak.babyak_backend.chatroom.controller;


import Babyak.babyak_backend.chatroom.domain.ChatRoom;
import Babyak.babyak_backend.chatroom.domain.Member;
import Babyak.babyak_backend.chatroom.dto.ChatRoomDto;
import Babyak.babyak_backend.chatroom.dto.FilterDto;
import Babyak.babyak_backend.chatroom.dto.UserDto;
import Babyak.babyak_backend.chatroom.service.ChatRoomService;
import Babyak.babyak_backend.chatroom.service.MemberService;
import Babyak.babyak_backend.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ChatRoomController {
    private final ChatRoomService chatroomservice;
    private final MemberService memberService;

    @Autowired
    public ChatRoomController(ChatRoomService chatroomservice, MemberService memberService) {
        this.chatroomservice = chatroomservice;
        this.memberService=memberService;
    }

    @GetMapping("/posting")
    @ResponseBody
    public String posting(
            @RequestBody ChatRoomDto chatRoomDto,
            @RequestBody UserDto userDto
    ) {
        User user = userDto.toEntity();
        ChatRoom ch = chatRoomDto.toEntity();
        Member m = new Member();
        m.setChatRoom(ch);
        chatroomservice.post(ch);
        memberService.create(m);

        return "posting finished";
    }

    @GetMapping("/AllList")
    @ResponseBody
    public List allList(){
        return chatroomservice.findAllChatRooms();
    }


}


