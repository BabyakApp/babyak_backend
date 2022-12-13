package Babyak.babyak_backend.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoDto {
    private String name;
    private String token;

    @Builder
    public UserInfoDto (String name, String token) {
        this.name = name;
        this.token = token;
    }
}
