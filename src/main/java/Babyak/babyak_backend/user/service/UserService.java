package Babyak.babyak_backend.user.service;

import Babyak.babyak_backend.user.dto.NoShowRequest;
import Babyak.babyak_backend.user.dto.NoShowResponse;
import Babyak.babyak_backend.user.dto.SignUpRequest;
import Babyak.babyak_backend.user.entity.Block;
import Babyak.babyak_backend.user.entity.Major;
import Babyak.babyak_backend.user.entity.User;
import Babyak.babyak_backend.user.repository.BlockRepository;
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
    private final BlockRepository blockRepository;

    /* 회원 가입 */
    public Void signUp(SignUpRequest signUpRequest) {

        String email = signUpRequest.getEmail();
        String nickname = signUpRequest.getNickname();
        //String departure = signUpRequest.getDeparture();
        String major = signUpRequest.getMajor();
        Long studentId = signUpRequest.getStudentId();

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

    /* 노쇼 및 차단 여부 업데이트 */
    public NoShowResponse updateNoShow(NoShowRequest noShowRequest) {
        String email = noShowRequest.getEmail();

        userQuerydslRepository.updateNoShow(email);

        int numOfNoShow = userQuerydslRepository.getNoShow(email);

        // 노쇼 횟수 = 3 -> 유저 차단
        if (numOfNoShow == 3) {
            User user = userQuerydslRepository.findByEmail(email);
            blockRepository.save(Block.builder()
                    .user(user)
                    .build());

            return new NoShowResponse(email, numOfNoShow, true);

        }

        return new NoShowResponse(email, numOfNoShow, false);
    }
}
