package kr.co.noticeboard.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import kr.co.noticeboard.domain.dto.response.CommentResDTO;
import lombok.*;

import java.util.List;

public class PostReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        @NotBlank(message = "게시글 제목을 입력해주세요.")
        private String title;

        @NotBlank(message = "게시글 본문을 입력해주세요.")
        private String content;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UPDATE {

        private String title;

        private String content;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CONDITION {

        private List<Long> postIds;
    }
}
