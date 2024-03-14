package kr.co.noticeboard.domain.dto.response;

import kr.co.noticeboard.domain.entity.DeleteStatus;
import lombok.*;

public class CommentResDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class READ {

        private String username;

        private String comment;

        private DeleteStatus status;
    }
}
