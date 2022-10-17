package Babyak.babyak_backend.controller;


import Babyak.babyak_backend.domain.chatroom.ChatRoom;
import Babyak.babyak_backend.service.chatroom.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ChatRoomController {
    private final ChatRoomService chatroomservice;

    @Autowired
    public ChatRoomController(ChatRoomService chatroomservice) {
        this.chatroomservice = chatroomservice;
    }

    @GetMapping("/posting")
    @ResponseBody
    public String posting(
            @RequestParam("chatTitle") String title,
            @RequestParam("time") String time,
            @RequestParam("people") int people,
            @RequestParam("food") String food,
            @RequestParam("location") String location,
            @RequestParam("content") String content
    ) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setChatTitle(title);
        chatRoom.setTime(time);
        chatRoom.setPeople(people);
        chatRoom.setFood(food);
        chatRoom.setLocation(location);
        chatRoom.setContent(content);

        chatroomservice.post(chatRoom);

        return "posting finished";
    }

    @GetMapping("/AllList")
    @ResponseBody
    public List allList(){
        return chatroomservice.findAllChatRooms();
    }





}


