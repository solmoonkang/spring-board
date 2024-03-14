package kr.co.noticeboard.domain.repository;

import kr.co.noticeboard.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    Page<Post> findAllPostsOrderByCreatedAtDesc(Pageable pageable);
}
