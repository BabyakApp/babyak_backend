package Babyak.babyak_backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoShowResponse {
    private String email;
    private int noShow;
    private Boolean isBlocked;
}
