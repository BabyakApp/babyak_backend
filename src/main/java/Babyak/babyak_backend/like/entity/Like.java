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
@Data
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long likeId;

    @Column
    private String email;

    @Column
    private Long postId;

    public Like (String email, Long postId) {
        this.email = email;
        this.postId = postId;
    }
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
    }
     */
}
