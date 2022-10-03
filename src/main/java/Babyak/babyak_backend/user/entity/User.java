package Babyak.babyak_backend.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @OneToOne
    @JoinColumn(name = "major_id")
    private Major majorId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "no_shows", nullable = false)
    private int noShows;

    @Column(nullable = false)
    private String email;


}
