package kr.co.noticeboard.service;

import kr.co.noticeboard.service.dto.request.MemberReqDTO;
import kr.co.noticeboard.service.dto.response.MemberResDTO;
import kr.co.noticeboard.domain.entity.Member;
import kr.co.noticeboard.domain.repository.MemberRepository;
import kr.co.noticeboard.infra.exception.DuplicatedException;
import kr.co.noticeboard.infra.exception.NotFoundException;
import kr.co.noticeboard.infra.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createMember(MemberReqDTO.CREATE create) {

        verifyMemberEmailExistsOrThrow(create.getEmail());

        String encodedPassword = passwordEncoder.encode(create.getPassword());

        final Member member = Member.toMemberEntity(create, encodedPassword);

        memberRepository.save(member);
    }

    public MemberResDTO.READ findMemberByEmail(String email) {

        final Member findMember = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND));

        return findMember.toReadDto();
    }

    public List<MemberResDTO.READ> findAllMember() {

        final List<Member> findAllMember = memberRepository.findAll();

        return findAllMember.stream().map(Member::toReadDto).collect(Collectors.toList());
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

    private void verifyMemberEmailExistsOrThrow(String email) {

        if (memberRepository.existsByEmail(email)) {
            throw new DuplicatedException(ResponseStatus.FAIL_EMAIL_DUPLICATION);
        }
    }
}
