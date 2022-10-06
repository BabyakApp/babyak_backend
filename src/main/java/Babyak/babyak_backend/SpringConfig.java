package Babyak.babyak_backend;

import Babyak.babyak_backend.domain.chatroom.ChatroomRepository;
import Babyak.babyak_backend.domain.chatroom.MemoryChatroomRepository;
import Babyak.babyak_backend.service.chatroom.ChatRoomService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링 빈 등록
@Configuration
public class SpringConfig {
    @Bean
    public ChatRoomService chatroomService(){
        return new ChatRoomService(chatroomRepository());
    }
    @Bean
    public ChatroomRepository chatroomRepository(){
        return new MemoryChatroomRepository();
    }

}
