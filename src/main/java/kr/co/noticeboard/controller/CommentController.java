package kr.co.noticeboard.controller;

import kr.co.noticeboard.domain.dto.request.CommentReqDTO;
import kr.co.noticeboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{member_id}/{post_id}")
    public ResponseEntity<Void> createComment(@PathVariable(name = "member_id") Long memberId,
                                              @PathVariable(name = "post_id") Long postId,
                                              @RequestBody @Validated CommentReqDTO.CREATE create) {
        commentService.createComment(memberId, postId, create);

        return ResponseEntity.ok().build();
    }
}
