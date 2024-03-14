package kr.co.noticeboard.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class CommentReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CREATE {

        @NotBlank(message = "댓글을 입력해주세요.")
        private String comment;
    }
}
