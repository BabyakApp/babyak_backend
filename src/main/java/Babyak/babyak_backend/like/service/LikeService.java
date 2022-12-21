package Babyak.babyak_backend.like.service;

import Babyak.babyak_backend.like.dto.LikeListRequest;
import Babyak.babyak_backend.like.dto.LikeListResponse;
import Babyak.babyak_backend.like.dto.LikeRequest;
import Babyak.babyak_backend.like.dto.UnlikeRequest;
import Babyak.babyak_backend.like.entity.Like;
import Babyak.babyak_backend.like.repository.LikeQuerydslRepository;
import Babyak.babyak_backend.like.repository.LikeRepository;
import Babyak.babyak_backend.post.entity.Post;
import Babyak.babyak_backend.post.repository.PostQuerydslRepository;
import Babyak.babyak_backend.post.repository.PostRepository;
import Babyak.babyak_backend.user.entity.User;
import Babyak.babyak_backend.user.repository.UserQuerydslRepository;
import Babyak.babyak_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeQuerydslRepository likeQuerydslRepository;
    private final PostQuerydslRepository postQuerydslRepository;
    private final UserQuerydslRepository userQuerydslRepository;

    @Transactional
    public void like (LikeRequest likeRequest) {
        User user = userQuerydslRepository.findByEmail(likeRequest.getEmail());
        Post post = postQuerydslRepository.findByPostId(likeRequest.getPostId());

        Like like = new Like(post, user);

        likeRepository.save(like);
    }

    @Transactional
    public void unlike (UnlikeRequest unlikeRequest) {
        User user = userQuerydslRepository.findByEmail(unlikeRequest.getEmail());
        Post post = postQuerydslRepository.findByPostId(unlikeRequest.getPostId());

        Like like = new Like(post, user);

        likeRepository.delete(like);
    }

    @Transactional(readOnly = true)
    public List<LikeListResponse> getLikeList (LikeListRequest likeListRequest) {
        Long userId = userQuerydslRepository.findByEmail(likeListRequest.getEmail()).getId();

        return likeQuerydslRepository.findLikeListByUserId(userId);
    }

}
