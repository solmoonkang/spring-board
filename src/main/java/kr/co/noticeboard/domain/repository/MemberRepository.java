package kr.co.noticeboard.domain.repository;

import kr.co.noticeboard.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
