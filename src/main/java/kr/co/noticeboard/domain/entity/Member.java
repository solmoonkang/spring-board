package kr.co.noticeboard.domain.entity;

import jakarta.persistence.*;
import kr.co.noticeboard.domain.dto.request.MemberReqDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_members")
@AttributeOverride(
        name = "id",
        column = @Column(name = "member_id", length = 4)
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Column(name = "email", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "name", length = 30)
    private String name;

    @Builder
    private Member(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public static Member createMember(MemberReqDTO.CREATE create) {
        return Member.builder()
                .email(create.getEmail())
                .name(create.getName())
                .build();
    }
}
