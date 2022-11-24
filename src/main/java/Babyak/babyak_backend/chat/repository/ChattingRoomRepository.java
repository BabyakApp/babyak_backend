package Babyak.babyak_backend.chat.repository;

import Babyak.babyak_backend.chat.dto.ChatRoomDto;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Repository
public class ChattingRoomRepository {
    private Map<String, ChatRoomDto> chatRoomMap; // 서버에 생성된 모든 채팅방의 정보를 모아둠

    // 채팅방 정보 저장은 일단 외부 저장소 이용 안 하고 HashMap에 저장

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    /* 채팅방 조회: 채팅방 Map에 저장된 정보 조회 */
    public List<ChatRoomDto> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoomDto findRoomById (String id) {
        return chatRoomMap.get(id);
    }

    /* 채팅방 생성: random UUID로 구별 ID를 가진 채팅방 객체 생성 후 채팅방 Map에 추가 */
    public ChatRoomDto createChatRoom (String name) {
        ChatRoomDto chatRoom = ChatRoomDto.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    /* 메세지 발송: 지정한 WebSocket 세션에 메세지 발송
    public <T> void sendMessage (WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
     */
}
