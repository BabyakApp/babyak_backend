package Babyak.babyak_backend.like.service;

import Babyak.babyak_backend.like.dto.LikeListRequest;
import Babyak.babyak_backend.like.dto.LikeListResponse;
import Babyak.babyak_backend.like.dto.LikeRequest;
import Babyak.babyak_backend.like.dto.UnlikeRequest;
import Babyak.babyak_backend.like.entity.Likes;
import Babyak.babyak_backend.like.repository.LikeQuerydslRepository;
import Babyak.babyak_backend.like.repository.LikeRepository;
import Babyak.babyak_backend.post.repository.PostQuerydslRepository;
import Babyak.babyak_backend.user.repository.UserQuerydslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        //User user = userQuerydslRepository.findByEmail(likeRequest.getEmail());
        //Post post = postQuerydslRepository.findByPostId(likeRequest.getPostId());

        Likes likes = new Likes(likeRequest.getEmail(), likeRequest.getPostId());

        likeRepository.save(likes);
    }

    @Transactional
    public void unlike (UnlikeRequest unlikeRequest) {
        //User user = userQuerydslRepository.findByEmail(unlikeRequest.getEmail());
        //Post post = postQuerydslRepository.findByPostId(unlikeRequest.getPostId());

        Likes likes = new Likes(unlikeRequest.getEmail(), unlikeRequest.getPostId());

        likeRepository.delete(likes);
    }

    @Transactional(readOnly = true)
    public List<LikeListResponse> getLikeList (LikeListRequest likeListRequest) {
        List<Long> postIdList = likeQuerydslRepository.findLikeListByEmail(likeListRequest.getEmail());

        List<LikeListResponse> likeList = new ArrayList<>();
        for (Long postId : postIdList) {
            likeList.add(postQuerydslRepository.findLikeListByPostId(postId));
        }
        return likeList;
    }

}
