package kr.co.noticeboard.service;

import kr.co.noticeboard.domain.dto.request.CommentReqDTO;
import kr.co.noticeboard.domain.entity.Comment;
import kr.co.noticeboard.domain.entity.DeleteStatus;
import kr.co.noticeboard.domain.entity.Member;
import kr.co.noticeboard.domain.entity.Post;
import kr.co.noticeboard.domain.repository.CommentRepository;
import kr.co.noticeboard.domain.repository.MemberRepository;
import kr.co.noticeboard.domain.repository.PostRepository;
import kr.co.noticeboard.infra.exception.NotFoundException;
import kr.co.noticeboard.infra.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(Long memberId, Long postId, CommentReqDTO.CREATE create) {

        final Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_POST_NOT_FOUND));

        final Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND));

        final Comment comment = Comment.toCommentEntity(findPost, findMember, create);

        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long memberId,
                              Long postId,
                              Long commentId,
                              CommentReqDTO.UPDATE update) {

        verifyMemberExistsOrThrow(memberId);
        verifyPostExistsOrThrow(postId);

        final Comment updateComment = commentRepository.findById(commentId)
                        .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_COMMENT_NOT_FOUND));

        verifyCommentNotDeletedOrThrow(updateComment);

        updateComment.updateComment(update);
    }

    @Transactional
    public void deleteComment(Long memberId, Long postId, Long commentId) {

        verifyMemberExistsOrThrow(memberId);
        verifyPostExistsOrThrow(postId);

        final Comment deleteComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        verifyCommentNotDeletedOrThrow(deleteComment);

        deleteComment.markAsDeleted();
    }

    private void verifyMemberExistsOrThrow(Long memberId) {

        if (!memberRepository.existsById(memberId)) {
            throw new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND);
        }
    }

    private void verifyPostExistsOrThrow(Long postId) {

        if (!postRepository.existsById(postId)) {
            throw new NotFoundException(ResponseStatus.FAIL_POST_NOT_FOUND);
        }
    }

    private void verifyCommentNotDeletedOrThrow(Comment comment) {

        if (comment.getStatus() == DeleteStatus.DELETED) {
            throw new NotFoundException(ResponseStatus.FAIL_POST_ALREADY_DELETED);
        }
    }
}
