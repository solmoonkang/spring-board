package kr.co.noticeboard.domain.repository;

import kr.co.noticeboard.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findMemberByName(String name);
}
