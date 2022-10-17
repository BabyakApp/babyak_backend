package Babyak.babyak_backend.domain.chatroom;

import Babyak.babyak_backend.user.entity.User;
import lombok.*;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chatroom_id", nullable = false)
    private Long chatroomid;

    @Column()
    private Timestamp lastChat;
    @Column(nullable = false)
    private String chatTitle;
    @Column(nullable = false)
    private String time;
    @Column(nullable = false)
    private int people;
    @Column(nullable = false)
    private String food;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String content;



}

