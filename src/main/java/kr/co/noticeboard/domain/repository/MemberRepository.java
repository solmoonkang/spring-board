package kr.co.noticeboard.domain.repository;

import kr.co.noticeboard.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    Optional<Member> findMemberByEmail(String email);
}
