package Babyak.babyak_backend.chatroom.dto;

import Babyak.babyak_backend.user.entity.Major;
import Babyak.babyak_backend.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String nickname;
    private Major majorId;
    private Long studentId;
    private int noShows;
    private String email;

    public User toEntity(){
        User user = User.builder()
                .id(id)
                .nickname(nickname)
                .majorId(majorId)
                .studentId(studentId)
                .noShows(noShows)
                .email(email)
                .build();
        return user;
    }


}
