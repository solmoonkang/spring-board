package kr.co.noticeboard.controller;

import kr.co.noticeboard.domain.dto.request.MemberReqDTO;
import kr.co.noticeboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
