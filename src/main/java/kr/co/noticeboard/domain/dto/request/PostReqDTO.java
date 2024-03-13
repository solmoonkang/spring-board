package kr.co.noticeboard.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class PostReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CREATE {

        @NotBlank(message = "게시글 제목을 입력해주세요.")
        private String title;

        @NotBlank(message = "게시글 본문을 입력해주세요.")
        private String content;
    }
}
