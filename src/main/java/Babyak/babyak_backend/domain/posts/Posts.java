package Babyak.babyak_backend.domain.posts;

import Babyak.babyak_backend.domain.posts.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500, nullable = false)
    private String title;
    //@Column(columnDefinition = "TEXT", nullable = false)
    //private String content;
    @Column(nullable = false)
    private String wishTime;
    @Column(nullable = false)
    private String wishMenu;
    @Column(nullable = false)
    private int wishMember;
    @Column(nullable = false)
    private int currentMember;
    @Column()
    private boolean isFull;


    private String author;

    @Builder
    public Posts(String title, String author, String wishtime, String wishmenu, int wishmember) {
        this.title = title;
        this.author = author;
        this.wishTime = wishtime;
        this.wishMenu = wishmenu;
        this.wishMember = wishmember;
        this.currentMember = 1;
        this.isFull = false;
    }

}

