package kr.co.noticeboard.domain.entity;

import jakarta.persistence.*;
import kr.co.noticeboard.service.dto.request.MemberReqDTO;
import kr.co.noticeboard.service.dto.response.MemberResDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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

    @Column(name = "password", nullable = false)
    private String password;

    @Builder
    private Member(String email,
                   String name,
                   String password) {

        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static Member toMemberEntity(MemberReqDTO.CREATE create,
                                        String encodedPassword) {

        return Member.builder()
                .email(create.getEmail())
                .name(create.getName())
                .password(encodedPassword)
                .build();
    }

    public MemberResDTO.READ toReadDto() {

        return MemberResDTO.READ.builder()
                .email(email)
                .name(name)
                .build();
    }

    public void updateMember(MemberReqDTO.UPDATE update) {

        this.email = update.getEmail();
        this.name = update.getName();
    }
}
