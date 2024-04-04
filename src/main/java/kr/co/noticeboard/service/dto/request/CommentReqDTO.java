package kr.co.noticeboard.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class CommentReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        @NotBlank(message = "댓글을 입력해주세요.")
        private String comment;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UPDATE {

        private String comment;
    }
}
