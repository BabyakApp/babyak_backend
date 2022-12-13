package Babyak.babyak_backend.post.service;

import Babyak.babyak_backend.post.dto.PostRequest;
import Babyak.babyak_backend.post.dto.PostResponse;
import Babyak.babyak_backend.post.entity.Post;
import Babyak.babyak_backend.post.repository.PostQuerydslRepository;
import Babyak.babyak_backend.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostQuerydslRepository postQuerydslRepository;

    @Transactional
    public void creatPost(PostRequest postRequest) {

        postRepository.save(Post.builder()
                .title(postRequest.getTitle())
                .createdDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")))
                .meetingDate(postRequest.getMeetingDate())
                .meetingTime(postRequest.getMeetingTime())
                .meetingSite(postRequest.getMeetingSite())
                .numOfPeople(postRequest.getNumOfPeople())
                .preferredFood(postRequest.getPreferredFood())
                .detail(postRequest.getDetail())
                .nickname(postRequest.getNickname())
                .major(postRequest.getMajor())
                .build()
        );

    }

    public List<PostResponse> getAllPost() {
        List<PostResponse> postList = postQuerydslRepository.findAllPost();
        return postList;
    }
}
