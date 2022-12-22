package Babyak.babyak_backend.like.repository;

import Babyak.babyak_backend.like.entity.Like;
import Babyak.babyak_backend.post.entity.Post;
import Babyak.babyak_backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface LikeRepository extends JpaRepository<Like, Long> {

//    // User와 Post를 파라미터로 받아서 해당 post에 해당 user가 좋아요 누른 적 있는지 체크하는 용도
//    Optional<Like> findByUserAndPost(User user, Post post);

}
