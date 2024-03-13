package kr.co.noticeboard.domain.dto.response;

import lombok.*;

public class PostResDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class READ {

        private String title;

        private String memberName;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DETAIL {

        private String title;

        private String memberName;

        private String content;
    }
}
