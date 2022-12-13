package Babyak.babyak_backend.post.entity;

import Babyak.babyak_backend.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Builder
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column
    private String title;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "meeting_date")
    private String meetingDate;

    @Column(name = "meeting_time")
    private String meetingTime;

    @Column(name = "meeting_site")
    private String meetingSite;

    @Column(name = "num_of_people")
    private Integer numOfPeople;

    @Column(name = "preferred_food")
    private String preferredFood;

    @Column
    private String detail;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer;
     */

    @Column
    private String nickname;

    @Column
    private String major;


    public Post(Long postId, String title, String createdDate, String meetingDate, String meetingTime, String meetingSite,
                Integer numOfPeople, String preferredFood, String detail, String nickname, String major) {
        this.postId = postId;
        this.title = title;
        this.createdDate = createdDate;
        this.meetingDate = meetingDate;
        this.meetingTime = meetingTime;
        this.meetingSite = meetingSite;
        this.numOfPeople = numOfPeople;
        this.preferredFood = preferredFood;
        this.detail = detail;
        //this.writer = writer;
        this.nickname = nickname;
        this.major = major;
    }

}
