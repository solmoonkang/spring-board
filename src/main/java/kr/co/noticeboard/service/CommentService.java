package kr.co.noticeboard.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.noticeboard.domain.dto.request.CommentReqDTO;
import kr.co.noticeboard.domain.entity.Comment;
import kr.co.noticeboard.domain.entity.Member;
import kr.co.noticeboard.domain.entity.Post;
import kr.co.noticeboard.domain.repository.CommentRepository;
import kr.co.noticeboard.domain.repository.MemberRepository;
import kr.co.noticeboard.domain.repository.PostRepository;
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
        final Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        final Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        final Comment comment = Comment.builder()
                .member(findMember)
                .post(findPost)
                .comment(create.getComment())
                .build();

        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long memberId, Long postId, Long commentId, CommentReqDTO.UPDATE update) {
        checkMemberAuthorization(memberId);
        checkPostExists(postId);

        final Comment updateComment = commentRepository.findById(commentId)
                        .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        updateComment.updateComment(update);
    }

    @Transactional
    public void deleteComment(Long memberId, Long postId, Long commentId) {
        checkMemberAuthorization(memberId);
        checkPostExists(postId);

        final Comment deleteComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글을 찾을 수 없습니다."));

        deleteComment.markAsDeleted();
    }

    private void checkMemberAuthorization(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new EntityNotFoundException("회원을 찾을 수 없습니다.");
        }
    }

    private void checkPostExists(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new EntityNotFoundException("게시글을 찾을 수 없습니다.");
        }
    }
}
