package kr.co.noticeboard.service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class MemberReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        @Email(message = "이메일을 입력해주세요.")
        private String email;

        @NotBlank(message = "이름을 입력해주세요.")
        private String name;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UPDATE {

        private String email;

        private String name;
    }
}
