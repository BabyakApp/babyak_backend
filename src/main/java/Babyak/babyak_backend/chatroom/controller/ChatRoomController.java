package Babyak.babyak_backend.chatroom.controller;


import Babyak.babyak_backend.chatroom.domain.ChatRoom;
import Babyak.babyak_backend.chatroom.domain.Member;
import Babyak.babyak_backend.chatroom.dto.ChatRoomDto;
import Babyak.babyak_backend.chatroom.service.ChatRoomService;
import Babyak.babyak_backend.chatroom.service.MemberService;
import Babyak.babyak_backend.user.entity.User;
import Babyak.babyak_backend.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ChatRoomController {
    private final ChatRoomService chatroomservice;
    private final MemberService memberService;
    private final UserService userService;

    @Autowired
    public ChatRoomController(ChatRoomService chatroomservice, MemberService memberService, UserService userService) {
        this.chatroomservice = chatroomservice;
        this.memberService=memberService;
        this.userService=userService;
    }

    @PostMapping("/posting")
    @ResponseBody
    public ResponseEntity<Void> posting(
            @RequestBody ChatRoomDto chatRoomDto
    ) {
        ChatRoom ch = chatRoomDto.toEntity();
        Member m = new Member();
        User u = ch.getUser();
        m.setChatRoom(ch);
        m.setUser(u);
        chatroomservice.post(ch);
        memberService.create(m);
        log.info("채팅방이 생성되었습니다.");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/AllList")
    @ResponseBody
    public ResponseEntity<List> allList(){
        return ResponseEntity.status(HttpStatus.OK).body(chatroomservice.findAllChatRooms());
    }

}


