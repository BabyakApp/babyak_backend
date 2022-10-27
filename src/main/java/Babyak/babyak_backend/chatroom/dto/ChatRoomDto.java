package Babyak.babyak_backend.chatroom.dto;


import Babyak.babyak_backend.chatroom.domain.ChatRoom;
import Babyak.babyak_backend.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ChatRoomDto {

    private String chatTitle;
    private String time;
    private int people;
    private String food;
    private String location;
    private String content;

    public ChatRoom toEntity(){
        ChatRoom chatRoom = ChatRoom.builder()
                .chatTitle(chatTitle)
                .food(food)
                .people(people)
                .time(time)
                .location(location)
                .content(content)
                .build();
        return chatRoom;
    }

    @Builder
    public ChatRoomDto(String chatTitle, String time,
                       int people, String food, String location, String content){
        this.chatTitle=chatTitle;
        this.time=time;
        this.people = people;
        this.food = food;
        this.location = location;
        this.content = content;
    }

}
