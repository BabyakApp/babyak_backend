package Babyak.babyak_backend.post.controller;

import Babyak.babyak_backend.post.dto.PostRequest;
import Babyak.babyak_backend.post.dto.PostResponse;
import Babyak.babyak_backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class PostController {

    private final PostService postService;

    /* 글 작성 */
    @PostMapping
    public ResponseEntity<Void> createPost (@RequestBody PostRequest postRequest) {

        postService.creatPost(postRequest);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /* 전체 글 목록 반환 */
    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> getAllPost() {

        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPost());
    }
}
