package Babyak.babyak_backend.domain.chatroom;

import Babyak.babyak_backend.repository.chatroom.MemoryChatroomRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryChatroomRepositoryTest {
    MemoryChatroomRepository repository = new MemoryChatroomRepository();

    @Test
    public void save(){
        ChatRoom chatroom = new ChatRoom();
        chatroom.setName("test");

        repository.save(chatroom);

        ChatRoom result = repository.findById(chatroom.getChatroomid()).orElse(null);
        assertThat(result).isEqualTo(chatroom);
    }

    @Test
    public void findAll() {
        //given
        ChatRoom ch1 = new ChatRoom();
        ch1.setName("spring1");
        repository.save(ch1);
        ChatRoom ch2 = new ChatRoom();
        ch2.setName("spring2");
        repository.save(ch2);
        //when
        List<ChatRoom> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
    }

}
