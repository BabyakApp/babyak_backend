package Babyak.babyak_backend.user.repository;

import Babyak.babyak_backend.user.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {
}
