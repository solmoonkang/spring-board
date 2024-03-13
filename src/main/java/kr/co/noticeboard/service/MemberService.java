package kr.co.noticeboard.service;

import kr.co.noticeboard.domain.dto.request.MemberReqDTO;
import kr.co.noticeboard.domain.entity.Member;
import kr.co.noticeboard.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(MemberReqDTO.CREATE create) {
        final Member member = Member.createMember(create);
        memberRepository.save(member);
    }

}
