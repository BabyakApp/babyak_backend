package Babyak.babyak_backend.User.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Block {

    @Id
    @Column(name = "block_id")
    private Long blockId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
