package Babyak.babyak_backend.chat.dto;

import lombok.*;

//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
@Getter
@Setter
public class ChatMessageDto {

    public ChatMessageDto() {
    }

    @Builder
    public ChatMessageDto (MessageType type, String roomId, String sender, String message, long userCount) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.userCount = userCount;
    }
    public enum MessageType {
        // ENTER: 채팅방 입장
        // TALK: 대화하기
        // QUIT: 채팅방 퇴장
        ENTER, TALK, QUIT;
    }
    private MessageType type; // 메세지 타입
    private String roomId; // 채팅방 번호
    private String sender; // 메세지 보낸 사람
    private String message; // 메세지
    private long userCount; // 채팅방 내 인원 수: 채팅방에서 메세지가 전달될 때 인원 수 갱신 시 사용


    /*
    private Long id;
    private Long chatRoomId;
    private String writer;
    private String message;
    //private String region;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime regDate;
    */
}
