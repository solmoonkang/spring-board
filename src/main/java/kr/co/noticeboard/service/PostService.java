package kr.co.noticeboard.service;

import kr.co.noticeboard.domain.dto.request.PostReqDTO;
import kr.co.noticeboard.domain.dto.response.PostResDTO;
import kr.co.noticeboard.domain.entity.DeleteStatus;
import kr.co.noticeboard.domain.entity.Member;
import kr.co.noticeboard.domain.entity.Post;
import kr.co.noticeboard.domain.repository.MemberRepository;
import kr.co.noticeboard.domain.repository.PostRepository;
import kr.co.noticeboard.infra.exception.NotFoundException;
import kr.co.noticeboard.infra.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    @Transactional
    public void createPost(Long memberId, PostReqDTO.CREATE create) {

        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND));

        final Post post = Post.toPostEntity(member, create);

        postRepository.save(post);
    }

    public List<PostResDTO.READ> findAllPost(Pageable pageable) {

        final Page<Post> findPostPage = postRepository.findAllPostsOrderByCreatedAtDesc(pageable);

        return findPostPage.getContent().stream()
                .filter(post -> post.getStatus() == DeleteStatus.NOT_DELETED)
                .map(Post::toReadDto)
                .collect(Collectors.toList());
    }

    public PostResDTO.DETAIL findPostById(Long postId) {

        final Post findPost = findPostOrThrow(postId);

        return findPost.toReadDetailDto();
    }

    @Transactional
    public void updatePost(Long postId,
                           Long memberId,
                           PostReqDTO.UPDATE update) {

        final Post updatePost = findPostOrThrow(postId);

        verifyMemberExistsOrThrow(updatePost, memberId);

        updatePost.updatePost(update);
    }

    @Transactional
    public void deletePost(Long postId, Long memberId) {

        final Post deletePost = findPostOrThrow(postId);

        verifyMemberExistsOrThrow(deletePost, memberId);

        deletePost.markAsDeleted();
    }

    public void verifyMemberExistsOrThrow(Post post, Long memberId) {

        if (!post.getMember().getId().equals(memberId)) {
            throw new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND);
        }
    }

    private Post findPostOrThrow(Long postId) {

        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_POST_NOT_FOUND));
    }
}
