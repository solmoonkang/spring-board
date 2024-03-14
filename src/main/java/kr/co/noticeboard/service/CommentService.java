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
@Transactional(readOnly = true)
public class CommentService {

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(Long memberId, Long postId, CommentReqDTO.CREATE create) {
        final Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        final Comment comment = Comment.builder()
                .member(findMember)
                .post(post)
                .comment(create.getComment())
                .build();

        commentRepository.save(comment);
    }
}
