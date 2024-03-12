package kr.co.noticeboard.service;

import kr.co.noticeboard.domain.dto.request.PostReqDTO;
import kr.co.noticeboard.domain.entity.Post;
import kr.co.noticeboard.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void createPost(PostReqDTO.CREATE create) {
        // TODO: Implement Validation
        final Post post = Post.builder()
                .title(create.getTitle())
                .name(create.getName())
                .content(create.getContent())
                .build();

        postRepository.save(post);
    }
}
