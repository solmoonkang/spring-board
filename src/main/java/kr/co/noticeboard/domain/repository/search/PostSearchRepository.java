package kr.co.noticeboard.domain.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.noticeboard.domain.dto.request.PostReqDTO;
import kr.co.noticeboard.domain.entity.Post;
import kr.co.noticeboard.domain.entity.QMember;
import kr.co.noticeboard.domain.entity.QPost;
import kr.co.noticeboard.infra.exception.NotFoundException;
import kr.co.noticeboard.infra.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QPost qPost = QPost.post;

    public Page<Post> search(Pageable pageable, PostReqDTO.CONDITION condition) {

        List<Post> posts = queryFactory
                .selectFrom(qPost)
                .leftJoin(qPost.member, QMember.member).fetchJoin()
                .where(
                        postIdsIn(condition.getPostIds())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = Optional.ofNullable(queryFactory
                .select(QPost.post.count())
                .from(QPost.post)
                .where(
                        postIdsIn(condition.getPostIds())
                )
                .fetchOne())
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_POST_NOT_FOUND));

        return new PageImpl<>(posts, pageable, total);
    }

    private BooleanExpression postIdsIn(List<Long> postIds) {
        return postIds == null || postIds.isEmpty() ? null : qPost.id.in(postIds);
    }
}
