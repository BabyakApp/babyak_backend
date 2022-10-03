package Babyak.babyak_backend.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GoogleResponse {
    private String accessToken;
    private String tokenType;
}
