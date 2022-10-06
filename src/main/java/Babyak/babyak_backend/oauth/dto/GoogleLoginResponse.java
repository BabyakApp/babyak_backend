package Babyak.babyak_backend.oauth.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class GoogleLoginResponse {

    private String email;
    private Boolean isEwha;

    /*
    private String accessToken; // 어플리케이션이 Google Login API 요청 승인을 위해 보내는 토큰
    private String expiresIn; // accessToken의 남은 수명
    private String refreshToken; // 새 accessToken을 얻는 데 사용할 수 있는 토큰
    private String scope;
    private String tokenType; // 반환된 토큰의 유형(여기선 Bearer 고정)
    private String idToken;
     */

}
