package kr.co.noticeboard.controller;

import kr.co.noticeboard.service.dto.request.PostReqDTO;
import kr.co.noticeboard.service.dto.response.PostResDTO;
import kr.co.noticeboard.infra.response.ResponseFormat;
import kr.co.noticeboard.infra.response.ResponseStatus;
import kr.co.noticeboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/{member_id}")
    public ResponseFormat<Void> createPost(@PathVariable(name = "member_id") Long memberId,
                                           @RequestBody @Validated PostReqDTO.CREATE create) {

        postService.createPost(memberId, create);

        return ResponseFormat.successMessage(ResponseStatus.SUCCESS_CREATED);
    }

    @GetMapping
    public ResponseFormat<List<PostResDTO.READ>> findAllPost(
            @RequestParam(name = "last_post_created_at", required = false) LocalDateTime lastPostCreatedAt) {

        return ResponseFormat.successMessageWithData(ResponseStatus.SUCCESS_EXECUTE,
                postService.findAllPost(lastPostCreatedAt)
        );
    }

    @GetMapping("/{post_id}")
    public ResponseFormat<PostResDTO.DETAIL> findPostWithCommentById(@PathVariable(name = "post_id") Long postId) {

        return ResponseFormat.successMessageWithData(ResponseStatus.SUCCESS_EXECUTE,
                postService.findPostWithCommentById(postId)
        );
    }

    @PutMapping("/{post_id}/{member_id}")
    public ResponseFormat<Void> updatePost(@PathVariable(name = "post_id") Long postId,
                                           @PathVariable(name = "member_id") Long memberId,
                                           @RequestBody @Validated PostReqDTO.UPDATE update) {

        postService.updatePost(postId, memberId, update);

        return ResponseFormat.successMessage(ResponseStatus.SUCCESS_EXECUTE);
    }

    @DeleteMapping("/{post_id}/{member_id}")
    public ResponseFormat<Void> deletePost(@PathVariable(name = "post_id") Long postId,
                                           @PathVariable(name = "member_id") Long memberId) {

        postService.deletePost(postId, memberId);

        return ResponseFormat.successMessage(ResponseStatus.SUCCESS_EXECUTE);
    }
}
