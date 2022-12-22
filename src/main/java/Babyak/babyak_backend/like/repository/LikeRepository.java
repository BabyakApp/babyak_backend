package Babyak.babyak_backend.like.repository;

import Babyak.babyak_backend.like.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface LikeRepository extends JpaRepository<Likes, Long> {

//    // User와 Post를 파라미터로 받아서 해당 post에 해당 user가 좋아요 누른 적 있는지 체크하는 용도
//    Optional<Likes> findByUserAndPost(User user, Post post);

}
