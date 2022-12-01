package Babyak.babyak_backend.chatroom.repository;

import Babyak.babyak_backend.chatroom.domain.ChatRoom;
import Babyak.babyak_backend.chatroom.dto.FilterDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChatroomRepository extends JpaRepository<ChatRoom, Long> {


}
