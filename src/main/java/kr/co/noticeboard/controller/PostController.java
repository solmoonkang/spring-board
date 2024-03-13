package kr.co.noticeboard.controller;

import kr.co.noticeboard.domain.dto.request.PostReqDTO;
import kr.co.noticeboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/{member_id}")
    public ResponseEntity<Void> createPost(@PathVariable(name = "member_id") Long memberId,
                                           @RequestBody @Validated PostReqDTO.CREATE create) {
        postService.createPost(memberId, create);

        return ResponseEntity.ok().build();
    }
}
