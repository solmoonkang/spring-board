package kr.co.noticeboard.service;

import kr.co.noticeboard.domain.dto.request.PostReqDTO;
import kr.co.noticeboard.domain.dto.response.PostResDTO;
import kr.co.noticeboard.domain.entity.DeleteStatus;
import kr.co.noticeboard.domain.entity.Member;
import kr.co.noticeboard.domain.entity.Post;
import kr.co.noticeboard.domain.repository.MemberRepository;
import kr.co.noticeboard.domain.repository.PostRepository;
import kr.co.noticeboard.domain.repository.search.PostSearchRepository;
import kr.co.noticeboard.infra.exception.NotFoundException;
import kr.co.noticeboard.infra.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    private final PostSearchRepository postSearchRepository;

    @Transactional
    public void createPost(Long memberId, PostReqDTO.CREATE create) {

        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND));

        final Post post = Post.toPostEntity(member, create);

        postRepository.save(post);
    }

    public List<PostResDTO.READ> findAllPost(Pageable pageable, PostReqDTO.CONDITION condition) {

        final Page<Post> findPostsPage = postSearchRepository.findAllPost(pageable, condition);

        return findPostsPage.getContent().stream()
                .filter(post -> post.getStatus() == DeleteStatus.NOT_DELETED)
                .map(Post::toReadDto)
                .collect(Collectors.toList());
    }

    public PostResDTO.DETAIL findPostWithCommentById(Long postId) {

        final Post findPost = postSearchRepository.findPostWithCommentsById(postId);

        verifyPostNotDeletedOrThrow(findPost);

        return findPost.toReadDetailDto();
    }

    @Transactional
    public void updatePost(Long postId, Long memberId, PostReqDTO.UPDATE update) {

        final Post updatePost = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_POST_NOT_FOUND));

        verifyMemberExistsOrThrow(updatePost, memberId);
        verifyPostNotDeletedOrThrow(updatePost);

        updatePost.updatePost(update);
    }

    @Transactional
    public void deletePost(Long postId, Long memberId) {

        final Post deletePost = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_POST_NOT_FOUND));

        verifyMemberExistsOrThrow(deletePost, memberId);
        verifyPostNotDeletedOrThrow(deletePost);

        deletePost.markAsDeleted();
    }

    private void verifyMemberExistsOrThrow(Post post, Long memberId) {

        if (!post.getMember().getId().equals(memberId)) {
            throw new NotFoundException(ResponseStatus.FAIL_MEMBER_NOT_FOUND);
        }
    }

    private void verifyPostNotDeletedOrThrow(Post post) {

        if (post.getStatus() == DeleteStatus.DELETED) {
            throw new NotFoundException(ResponseStatus.FAIL_POST_ALREADY_DELETED);
        }
    }
}
