package Babyak.babyak_backend.like.controller;

import Babyak.babyak_backend.like.dto.LikeListRequest;
import Babyak.babyak_backend.like.dto.LikeListResponse;
import Babyak.babyak_backend.like.dto.LikeRequest;
import Babyak.babyak_backend.like.dto.UnlikeRequest;
import Babyak.babyak_backend.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like")
    public ResponseEntity<Void> like (@RequestBody LikeRequest likeRequest) {
        likeService.like(likeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/like")
    public ResponseEntity<Void> unlike (@RequestBody UnlikeRequest unlikeRequest) {
        likeService.unlike(unlikeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/likelist")
    public ResponseEntity<List<LikeListResponse>> getLikeList (@RequestBody LikeListRequest likeListRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(likeService.getLikeList(likeListRequest));
    }

}
