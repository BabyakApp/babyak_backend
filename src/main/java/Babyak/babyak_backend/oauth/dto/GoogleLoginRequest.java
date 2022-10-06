package Babyak.babyak_backend.oauth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleLoginRequest {

    private String clientId; // 어플리케이션의 클라이언트 id
    private String redirectUri; // 구글 로그인 후 redirect 위치
    private String clientSecret;
    private String responseType; // Google Oauth 2.0 엔드포인트의 인증 코드 반환 여부
    private String scope; // Oauth 동의 범위
    private String code;
    private String accessType; // 사용자가 브라우저에 없을 때 어플리케이션이 액세스 토큰을 새로 고칠 수 있는지 여부
    private String grantType;
    private String state;
    private String includeGrantedScopes; // 어플리케이션이 컨텍스트에서 추가 범위에 대한 액세스를 요청하기ㅏ 위해 추가 권한 부여를 사용
    private String loginHint; // 어플리케이션이 인증하려는 사용자를 알고 있는 경우, 이 매개변수를 사용하여 Google 인증 서버에 힌트 제공
    private String prompt; // default: 처음으로 액세스를 요청할 때만 사용자에게 메세지 표시

}
