package Babyak.babyak_backend.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Major {

    @Id
    @Column(name = "major_id")
    private int majorId;

    @Column(nullable = false)
    private String depart;

    @Column(nullable = false)
    private String major;
}
