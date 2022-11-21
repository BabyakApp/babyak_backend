package Babyak.babyak_backend.chat.dto;

import Babyak.babyak_backend.chat.service.ChatService;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//@Data
@Getter
public class ChatRoomDto {

    private String roomId;
    private String name; // 채팅방 이름
    private Set<WebSocketSession> sessions = new HashSet<>(); // 입장한 client들의 정보를 갖고 있어야 하므로

    @Builder
    public ChatRoomDto(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions (WebSocketSession session, ChatMessageDto chatMessage, ChatService chatService) {
        if (chatMessage.getType().equals(ChatMessageDto.MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
        }
        sendMessage(chatMessage, chatService);
    }

    public <T> void sendMessage (T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
