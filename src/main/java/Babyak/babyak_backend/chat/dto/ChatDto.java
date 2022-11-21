package Babyak.babyak_backend.chat.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;

import java.time.LocalDateTime;

//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
@Getter
@Setter
@NoArgsConstructor
@Data
public class ChatDto {

    private Long chatRoomId; // 구독 채널 구분할 수 있는 식별자
    private String writer;
    private String message;

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
