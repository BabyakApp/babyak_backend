package Babyak.babyak_backend.domain.chatroom;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
public class ChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id", nullable = false)
    private Long chatroomid;
    private Timestamp lastChat;

    public Long getId() {
        return chatroomid;
    }
}

