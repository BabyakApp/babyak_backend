package Babyak.babyak_backend.chat.dto;

import lombok.*;

//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
@Getter
@Setter
public class ChatMessageDto {

    public enum MessageType {
        // ENTER: 채팅방 입장
        // TALK: 대화하기
        //ENTER, TALK;
        ENTER, TALK, JOIN;
    }
    private MessageType type; // 메세지 타입
    private String roomId; // 채팅방 번호
    private String sender; // 메세지 보낸 사람
    private String message; // 메세

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
