package Babyak.babyak_backend.service.chatroom;


import Babyak.babyak_backend.domain.chatroom.ChatRoom;
import Babyak.babyak_backend.repository.chatroom.ChatroomRepository;
import Babyak.babyak_backend.repository.chatroom.MemoryChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@Transactional
public class ChatRoomService {

    private final ChatroomRepository chatroomrepository;
    @Autowired
    public ChatRoomService(ChatroomRepository chatroomrepository) {
        this.chatroomrepository = chatroomrepository;
    }

    /*
     * 글 작성(채팅방 생성)
     * */
    public Long post(ChatRoom chatroom){
        chatroomrepository.save(chatroom);
        return chatroom.getChatroomid();
    }

    /*
    * 전체 채팅방 조회
    * */
    public List<ChatRoom> findAllChatRooms(){
        return chatroomrepository.findAll();
    }

}
