package Babyak.babyak_backend.user.service;

import Babyak.babyak_backend.user.dto.LoginRequest;
import Babyak.babyak_backend.user.entity.Major;
import Babyak.babyak_backend.user.entity.User;
import Babyak.babyak_backend.user.repository.MajorQuerydslRepository;
import Babyak.babyak_backend.user.repository.UserQuerydslRepository;
import Babyak.babyak_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserQuerydslRepository userQuerydslRepository;
    private final MajorQuerydslRepository majorQuerydslRepository;

    /* 회원 가입 */
    public Void signUp(LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String nickname = loginRequest.getNickname();
        //String departure = loginRequest.getDeparture();
        String major = loginRequest.getMajor();
        Long studentId = loginRequest.getStudentId();

        // major -> major entity 찾기
        Major userMajor = majorQuerydslRepository.findByMajor(major);

        // User 저장
        userRepository.save(User.builder()
                .email(email)
                .nickname(nickname)
                .major(userMajor)
                .studentId(studentId)
                .noShows(0)
                .build());

        return null;
    }
}
