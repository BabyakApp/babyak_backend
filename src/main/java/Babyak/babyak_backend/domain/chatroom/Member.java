package Babyak.babyak_backend.domain.chatroom;


import Babyak.babyak_backend.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id", nullable = false)
    private Long memberid;

    @OneToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "visited_time")
    private Timestamp userLastVisited;
}
