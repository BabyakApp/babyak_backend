package Babyak.babyak_backend.controller;


import Babyak.babyak_backend.service.chatroom.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatRoomController {
    private final ChatRoomService chatroomservice;

    @Autowired
    public ChatRoomController(ChatRoomService chatroomservice) {
        this.chatroomservice = chatroomservice;
    }


    @GetMapping("posting")
    @ResponseBody
    public ChatRoomForm posting(
            @RequestParam("chatTitle") String title,
            @RequestParam("time") String time,
            @RequestParam("people") int people,
            @RequestParam("food") String food,
            @RequestParam("location") String location,
            @RequestParam("content") String content
    ) {
        ChatRoomForm chatroomform = new ChatRoomForm();
        chatroomform.setContent(title);
        chatroomform.setTime(time);
        chatroomform.setPeople(people);
        chatroomform.setFood(food);
        chatroomform.setLocation(location);
        chatroomform.setContent(content);
        return chatroomform;
    }
}


