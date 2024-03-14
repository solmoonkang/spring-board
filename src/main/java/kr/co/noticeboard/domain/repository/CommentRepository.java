package kr.co.noticeboard.domain.repository;

import kr.co.noticeboard.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
