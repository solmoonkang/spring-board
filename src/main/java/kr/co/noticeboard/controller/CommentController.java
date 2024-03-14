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

    @PutMapping("/{member_id}/{post_id}/{comment_id}")
    public ResponseEntity<Void> updateComment(@PathVariable(name = "member_id")  Long memberId,
                                              @PathVariable(name = "post_id")  Long postId,
                                              @PathVariable(name = "comment_id")  Long commentId,
                                              @RequestBody @Validated CommentReqDTO.UPDATE update) {
        commentService.updateComment(memberId, postId, commentId, update);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{member_id}/{post_id}/{comment_id}")
    public ResponseEntity<Void> deleteComment(@PathVariable(name = "member_id")  Long memberId,
                                              @PathVariable(name = "post_id")  Long postId,
                                              @PathVariable(name = "comment_id")  Long commentId) {
        commentService.deleteComment(memberId, postId, commentId);

        return ResponseEntity.ok().build();
    }
}
