package Babyak.babyak_backend.post.repository;

import Babyak.babyak_backend.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
