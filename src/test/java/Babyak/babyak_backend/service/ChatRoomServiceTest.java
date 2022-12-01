package Babyak.babyak_backend.service;

import Babyak.babyak_backend.chatroom.domain.ChatRoom;
import Babyak.babyak_backend.chatroom.repository.MemoryChatroomRepository;
import Babyak.babyak_backend.chatroom.service.ChatRoomService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChatRoomServiceTest {
    ChatRoomService chatroomservice;
    MemoryChatroomRepository chatroomrepository;

    @BeforeEach
    public void beforeEach() {
        chatroomrepository = new MemoryChatroomRepository();
        chatroomservice = new ChatRoomService(chatroomrepository);
    }

    @AfterEach
    public void afterEach() {
        chatroomrepository.deleteAll();
    }

    @Test
    public void 채팅방_생성_메모리() throws Exception {
        //Given
        ChatRoom ch = new ChatRoom();
        ch.setChatTitle("hello");
        //When
        Long saveId = chatroomservice.post(ch);
        //Then
        ChatRoom findMember = chatroomrepository.findById(saveId).get();
        assertEquals(ch.getChatTitle(), findMember.getChatTitle());
    }

}
