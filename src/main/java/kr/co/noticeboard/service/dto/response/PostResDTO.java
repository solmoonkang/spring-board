package kr.co.noticeboard.service.dto.response;

import kr.co.noticeboard.domain.entity.DeleteStatus;
import lombok.*;

import java.util.List;

public class PostResDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class READ {

        private String title;

        private String memberName;

        private DeleteStatus status;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DETAIL {

        private String title;

        private String memberName;

        private String content;

        private DeleteStatus status;

        private List<CommentResDTO.READ> comments;
    }
}
