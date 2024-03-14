package kr.co.noticeboard.service;

import kr.co.noticeboard.domain.dto.request.MemberReqDTO;
import kr.co.noticeboard.domain.dto.response.MemberResDTO;
import kr.co.noticeboard.domain.entity.Member;
import kr.co.noticeboard.domain.repository.MemberRepository;
import kr.co.noticeboard.infra.exception.NotFoundException;
import kr.co.noticeboard.infra.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(MemberReqDTO.CREATE create) {

        final Member member = Member.toMemberEntity(create);

        memberRepository.save(member);
    }

    public List<MemberResDTO.READ> findMemberByName(String name) {

        final List<Member> findMember = memberRepository.findMemberByName(name);

        return findMember.stream().map(Member::toReadDto).collect(Collectors.toList());
    }

    @Transactional
    public void updateMember(Long memberId, MemberReqDTO.UPDATE update) {

        final Member updateMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND));

        updateMember.updateMember(update);
    }

    @Transactional
    public void deleteMember(Long memberId) {

        final Member deleteMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND));

        memberRepository.delete(deleteMember);
    }
}
