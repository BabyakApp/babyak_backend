package Babyak.babyak_backend.like.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="like_id")
    private Long likeId;

    @Column
    private String email;

    @Column
    private Long postId;

    public Likes(String email, Long postId) {
        this.email = email;
        this.postId = postId;
    }
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Likes(Post post, User user) {
        this.post = post;
        this.user = user;
    }
     */
}
