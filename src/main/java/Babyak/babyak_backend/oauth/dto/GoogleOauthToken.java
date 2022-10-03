package Babyak.babyak_backend.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GoogleOauthToken {
    private String accessToken;
    private int expires;
    private String scope;
    private String tokenType;
    private String idToken;
}
