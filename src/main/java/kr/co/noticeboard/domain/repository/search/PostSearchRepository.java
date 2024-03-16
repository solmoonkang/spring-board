package kr.co.noticeboard.domain.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.noticeboard.domain.entity.Post;
import kr.co.noticeboard.domain.entity.QComment;
import kr.co.noticeboard.domain.entity.QMember;
import kr.co.noticeboard.domain.entity.QPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostSearchRepository {

    private static final long MAX_POSTS_LIMIT = 10;

    private final JPAQueryFactory queryFactory;

    private final QMember qMember = QMember.member;

    private final QPost qPost = QPost.post;

    private final QComment qComment = QComment.comment1;

    public List<Post> findAllPostByCursor(LocalDateTime lastPostCreatedAt) {

        return queryFactory
                .selectFrom(QPost.post)
                .leftJoin(QPost.post.member, QMember.member).fetchJoin()
                .where(afterLastPostCreatedAt(lastPostCreatedAt))
                .orderBy(QPost.post.createdAt.desc())
                .limit(MAX_POSTS_LIMIT)
                .fetch();
    }

    public Post findPostWithCommentsById(Long postId) {

        return queryFactory
                .selectFrom(qPost)
                .leftJoin(qPost.comments, qComment).fetchJoin()
                .leftJoin(qComment.member, qMember).fetchJoin()
                .where(qPost.id.eq(postId))
                .fetchOne();
    }

    private BooleanExpression afterLastPostCreatedAt(LocalDateTime lastPostCreatedAt) {
        return lastPostCreatedAt == null ? null : QPost.post.createdAt.after(lastPostCreatedAt);
    }
}
