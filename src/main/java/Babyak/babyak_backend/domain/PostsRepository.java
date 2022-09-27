package Babyak.babyak_backend.domain;

import Babyak.babyak_backend.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    List<Posts> findAllDesc();


}
