package Babyak.babyak_backend.user.controller;

import Babyak.babyak_backend.user.dto.LoginRequest;
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

    private UserService userService;

    /* 회원 가입 */
    @PostMapping("/login")
    public ResponseEntity<Void> signUp(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(loginRequest));
    }
}
