package Babyak.babyak_backend.repository.chatroom;

import Babyak.babyak_backend.domain.chatroom.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


public interface ChatroomRepository extends JpaRepository<ChatRoom, Long> {

}
