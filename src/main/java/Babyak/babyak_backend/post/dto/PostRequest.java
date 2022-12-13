package Babyak.babyak_backend.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {

    // 일단 직접 작성 -> 추후 수정
    private String nickname;
    private String major;

    private String title;

    private String meetingDate;
    private String meetingTime;
    private String meetingSite;

    private Integer numOfPeople;
    private String preferredFood;
    private String detail;

}
