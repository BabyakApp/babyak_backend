package Babyak.babyak_backend.oauth.service;

import Babyak.babyak_backend.oauth.dto.GoogleOauthToken;
import Babyak.babyak_backend.oauth.dto.GoogleRequest;
import Babyak.babyak_backend.oauth.dto.GoogleResponse;
import Babyak.babyak_backend.oauth.dto.GoogleUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final GoogleRequest googleRequest;
    private final HttpServletResponse response;

    public void request() throws IOException {
        String redirectURL;

        redirectURL = googleRequest.getOauthRedirectURL();

        // 예외처리 코드 추가

        response.sendRedirect(redirectURL);
    }

    public GoogleResponse oauthLogin(String code) throws IOException {
        // 구글로 일회성 코드 전송해 accessToken 담긴 응답 객체 받아옴
        ResponseEntity<String> accessTokenResponse = googleRequest.requestAccessToken(code);
        // 응답 객체는 JSON 형식 -> deserialization해 Java 객체에 담음
        GoogleOauthToken oauthToken = googleRequest.getAccessToken(accessTokenResponse);

        // accessToken 다시 구글로 보내 구글에 저장된 사용자 정보가 담긴 응답 객체 받아옴
        ResponseEntity<String> userInfoResponse = googleRequest.requestUserInfo(oauthToken);
        // 다시 JSON 형식의 응답 객체를 자바 객체로 역직렬화
        GoogleUser googleUser = googleRequest.getUserInfo(userInfoResponse);

        String userEmail = googleUser.getEmail();

        GoogleResponse googleResponse = new GoogleResponse(oauthToken.getAccessToken(), oauthToken.getTokenType());
        return googleResponse;
    }
}
