package Babyak.babyak_backend.oauth.controller;

import Babyak.babyak_backend.oauth.service.OauthService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class OauthController {

    private final OauthService oauthService;

    /* GOOGLE 로그인 */
    @GetMapping("/google")
    public ResponseEntity<Void> googleLogin() {
        System.out.println("login");
        oauthService.request();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping(value = "/google/redirect")
    public String googleRedirect(
            @RequestParam(name = "code") String code) {
        log.info("구글 로그인 API 서버로부터 받은 code: " + code);
        return oauthService.getUserInfo(code);
    }
}
