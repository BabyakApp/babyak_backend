package Babyak.babyak_backend.chat.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//@Data
@Getter
@Setter
public class ChatRoomDto {
    private String roomId;
    private String roomTitle;

    // WebSocketSession: Spring에서 Websocket Connection이 맺어진 세션
    private Set<WebSocketSession> socketSessions = new HashSet<>();

    public static ChatRoomDto create(String roomTitle) {
        ChatRoomDto room = new ChatRoomDto();
        room.setRoomId(UUID.randomUUID().toString());
        room.setRoomTitle(roomTitle);
        return room;
    }
}
