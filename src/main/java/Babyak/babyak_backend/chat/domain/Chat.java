package Babyak.babyak_backend.chat.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class Chat {
    @Id
    @Column(name = "balloon_id", nullable = false)
    private Long balloonid;

    @Column()
    private String chatCreatedTime;

    @Column()
    private String chatContent;


}
