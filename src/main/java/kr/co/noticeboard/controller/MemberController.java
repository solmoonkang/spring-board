package kr.co.noticeboard.controller;

import kr.co.noticeboard.service.dto.request.MemberReqDTO;
import kr.co.noticeboard.service.dto.response.MemberResDTO;
import kr.co.noticeboard.infra.response.ResponseFormat;
import kr.co.noticeboard.infra.response.ResponseStatus;
import kr.co.noticeboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseFormat<Void> createMember(@RequestBody @Validated MemberReqDTO.CREATE create) {

        memberService.createMember(create);

        return ResponseFormat.successMessage(ResponseStatus.SUCCESS_CREATED);
    }

    @GetMapping("/{email}")
    public ResponseFormat<MemberResDTO.READ> findMemberByEmail(@PathVariable String email) {

        return ResponseFormat.successMessageWithData(ResponseStatus.SUCCESS_EXECUTE,
                memberService.findMemberByEmail(email)
        );
    }

    @GetMapping
    public ResponseFormat<List<MemberResDTO.READ>> findAllMember() {

        return ResponseFormat.successMessageWithData(ResponseStatus.SUCCESS_EXECUTE,
                memberService.findAllMember()
        );
    }

    @PutMapping("/{member_id}")
    public ResponseFormat<Void> updateMember(@PathVariable(name = "member_id") Long memberId,
                                             @RequestBody @Validated MemberReqDTO.UPDATE update) {

        memberService.updateMember(memberId, update);

        return ResponseFormat.successMessage(ResponseStatus.SUCCESS_EXECUTE);
    }

    @DeleteMapping("/{member_id}")
    public ResponseFormat<Void> deleteMember(@PathVariable(name = "member_id") Long memberId) {

        memberService.deleteMember(memberId);

        return ResponseFormat.successMessage(ResponseStatus.SUCCESS_EXECUTE);
    }
}
