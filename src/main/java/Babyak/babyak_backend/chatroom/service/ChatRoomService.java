package Babyak.babyak_backend.chatroom.service;


import Babyak.babyak_backend.chatroom.domain.ChatRoom;
import Babyak.babyak_backend.chatroom.dto.FilterDto;
import Babyak.babyak_backend.chatroom.repository.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    * 전체 채팅방 목록 반환
    * */
    public List<ChatRoom> findAllChatRooms(){
        return chatroomrepository.findAll();
    }


}
