package kr.co.noticeboard.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.noticeboard.domain.dto.request.PostReqDTO;
import kr.co.noticeboard.domain.dto.response.PostResDTO;
import kr.co.noticeboard.domain.entity.Member;
import kr.co.noticeboard.domain.entity.Post;
import kr.co.noticeboard.domain.repository.MemberRepository;
import kr.co.noticeboard.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public void createPost(Long memberId, PostReqDTO.CREATE create) {
        final Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("회원을 찾을 수 없습니다."));

        final Post post = Post.toPostEntity(member, create);
        postRepository.save(post);
    }

    public List<PostResDTO.READ> findAllPost() {
        final List<Post> findPost = postRepository.findAll();
        return findPost.stream().map(Post::toReadDto).collect(Collectors.toList());
    }

    public PostResDTO.DETAIL findPostById(Long postId) {
        final Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        return findPost.toReadDetailDto();
    }
}
