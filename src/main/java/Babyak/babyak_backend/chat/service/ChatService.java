package Babyak.babyak_backend.chat.service;

import Babyak.babyak_backend.chat.dto.ChatRoomDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoomDto> chatRooms; // 서버에 생성된 모든 채팅방의 정보를 모아둠

    // 채팅방 정보 저장은 일단 외부 저장소 이용 안 하고 HashMap에 저장

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    /* 채팅방 조회: 채팅방 Map에 저장된 정보 조회 */
    public List<ChatRoomDto> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    // 이 부분은 나중에 DB 사용해서
    public ChatRoomDto findRoomById (String roomId) {
        return chatRooms.get(roomId);
    }

    /* 채팅방 생성: random UUID로 구별 ID를 가진 채팅방 객체 생성 후 채팅방 Map에 추가 */
    public ChatRoomDto createRoom (String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoomDto chatRoom = ChatRoomDto.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    /* 메세지 발송: 지정한 WebSocket 세션에 메세지 발송 */
    public <T> void sendMessage (WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
