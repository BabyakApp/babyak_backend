package Babyak.babyak_backend.domain.chatroom;

import Babyak.babyak_backend.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface ChatroomRepository extends JpaRepository<ChatRoom, Long> {
    Map<Integer, Timestamp> userLastvisited();
    List<User> memberlist();
}
