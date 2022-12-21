package Babyak.babyak_backend.like.entity;

import Babyak.babyak_backend.post.entity.Post;
import Babyak.babyak_backend.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
