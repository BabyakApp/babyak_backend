package Babyak.babyak_backend.user.controller;

import Babyak.babyak_backend.user.dto.NoShowRequest;
import Babyak.babyak_backend.user.dto.NoShowResponse;
import Babyak.babyak_backend.user.dto.SignUpRequest;
import Babyak.babyak_backend.user.repository.BlockQuerydslRepository;
import Babyak.babyak_backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class UserController {

    private final UserService userService;
    private final BlockQuerydslRepository blockQuerydslRepository;

    /* 회원 가입 */
    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest) {
        log.info("가입되었습니다");
        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(signUpRequest));
    }

    /* 노쇼 업데이트 및 유저 차단 */
    @PutMapping("/noshow")
    public ResponseEntity<NoShowResponse> updateNoshow(@RequestBody NoShowRequest noShowRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateNoShow(noShowRequest));
    }

    @GetMapping("/blocked")
    public void getBlockInfo(String email) {

        if (blockQuerydslRepository.findByEmail(email) == null) {
            log.info("차단된 유저가 아닙니다.");
        }
        else {
            log.info("차단된 유저입니다.");
        }
    }
}
