package Babyak.babyak_backend.chat.dto;

import Babyak.babyak_backend.chat.service.ChatService;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//@Data
@Getter
@Setter
public class ChatRoomDto implements Serializable {

    /**
     * Redis 에 저장되는 객체들은 Serialize 가능해야하므로 Serializable 참조하고 serialVersionUID 셋팅
     */
    private static final long serialVersionUID = 6494678977089006639L;

    private String roomId;
    private String name; // 채팅방 이름
    private Long userCount; // 채팅방 인원 수

    public static ChatRoomDto create(String name) {
        ChatRoomDto chatRoom = new ChatRoomDto();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }

    /*
    - STOMP의 pub/sub 방식을 이용하면 구독자 관리 알아서 가능 -> 웹소켓 세션 관리 필요X
    - 발송의 구현도 알아서 해결 -> 일일이 클라이언트에게 메세지 발송하는 구현 안 해도 됨

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
     */
}
