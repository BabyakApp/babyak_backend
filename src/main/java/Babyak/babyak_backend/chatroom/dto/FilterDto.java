package Babyak.babyak_backend.chatroom.dto;


import Babyak.babyak_backend.chatroom.domain.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
@NoArgsConstructor
public class FilterDto {
    private String major;
    private String date;
    private int time;
    private String locate;
    private int people;
    private String food;

    @Override
    public String toString() {
        return "FilterDto{" +
                "major='" + major + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                ", locate='" + locate + '\'' +
                ", people=" + people +
                ", food='" + food + '\'' +
                '}';
    }

}
