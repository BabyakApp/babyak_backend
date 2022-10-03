package Babyak.babyak_backend.oauth.controller;

import Babyak.babyak_backend.oauth.dto.GoogleResponse;
import Babyak.babyak_backend.oauth.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {

    private final OauthService oauthService;

    /* GOOGLE 로그인 */
    @GetMapping("/google")
    public void googleLoginRedirect() throws IOException {
        oauthService.request();
    }

    /*
    Google Login API Server 요청에 의한 callback 처리
    @param code API Server 로부터 넘어오는 code
    @return Google Login 요청 결과로 받은 Json 형태의 java 객체 (access token, jwt token, user num 등)
     */
    @ResponseBody
    @GetMapping(value = "/google/callback")
    public ResponseEntity<GoogleResponse> callback(@RequestParam(name = "code") String code) throws IOException {
        System.out.println("API 서버로부터 받은 code: " + code);
        GoogleResponse googleResponse = oauthService.oauthLogin(code);
        return ResponseEntity.status(HttpStatus.OK).body(googleResponse);
    }
}
