package Babyak.babyak_backend.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {

    private String title;
    private String detail;

    private Integer currentPeople = 1;
    private Integer maxPeople;
    private String meetingSite;
    private String preferredFood;

    private String meetingDate;
    private String meetingTime;

    private String nickname;
    private String major;
}
