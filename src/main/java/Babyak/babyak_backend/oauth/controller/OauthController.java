package Babyak.babyak_backend.oauth.controller;

import Babyak.babyak_backend.oauth.dto.GoogleLoginResponse;
import Babyak.babyak_backend.oauth.service.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        oauthService.request();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping(value = "/google/redirect")
    public ResponseEntity<GoogleLoginResponse> googleRedirect(
            @RequestParam(name = "code") String code) {
        //log.info("구글 로그인 API 서버로부터 받은 code: " + code);
        GoogleLoginResponse googleLoginResponse = oauthService.getUserEmail(code);

        if (googleLoginResponse.getIsEwha() == false) {
            log.info("ewhain.net 계정으로 로그인 해주세요.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(googleLoginResponse);
        }
        else {
            log.info("ewhain.net 계정으로 로그인 되었습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(googleLoginResponse);
        }
    }
}
