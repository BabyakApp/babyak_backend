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
public class ChatDto {
    private Long id;
    private Long chatRoomId;
    private String writer;
    private String message;
    //private String region;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime regDate;
}
