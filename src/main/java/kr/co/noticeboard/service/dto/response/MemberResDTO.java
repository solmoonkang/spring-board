package kr.co.noticeboard.service.dto.response;

import lombok.*;

public class MemberResDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class READ {

        private String email;

        private String name;
    }
}
