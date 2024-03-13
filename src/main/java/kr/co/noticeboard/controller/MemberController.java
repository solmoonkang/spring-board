package kr.co.noticeboard.controller;

import kr.co.noticeboard.domain.dto.request.MemberReqDTO;
import kr.co.noticeboard.domain.dto.response.MemberResDTO;
import kr.co.noticeboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody @Validated MemberReqDTO.CREATE create) {
        memberService.createMember(create);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<MemberResDTO.READ>> findMemberByName(@PathVariable String name) {
        return ResponseEntity.ofNullable(memberService.findMemberByName(name));
    }

    @PutMapping("/{member_id}")
    public ResponseEntity<Void> updateMember(@PathVariable(name = "member_id") Long memberId,
                                             @RequestBody @Validated MemberReqDTO.UPDATE update) {
        memberService.updateMember(memberId, update);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{member_id}")
    public ResponseEntity<Void> deleteMember(@PathVariable(name = "member_id") Long memberId) {
        memberService.deleteMember(memberId);

        return ResponseEntity.ok().build();
    }
}
