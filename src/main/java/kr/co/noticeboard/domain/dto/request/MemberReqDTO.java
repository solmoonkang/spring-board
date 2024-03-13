package kr.co.noticeboard.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class MemberReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CREATE {

        @Email(message = "이메일을 입력해주세요.")
        private String email;

        @NotBlank(message = "이름을 입력해주세요.")
        private String name;
    }
}
