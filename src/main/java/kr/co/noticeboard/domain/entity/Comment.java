package kr.co.noticeboard.domain.entity;

import jakarta.persistence.*;
import kr.co.noticeboard.domain.dto.request.CommentReqDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "tbl_comments")
@AttributeOverride(
        name = "id",
        column = @Column(name = "comment_id", length = 4)
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "comment", length = 100)
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DeleteStatus status = DeleteStatus.NOT_DELETED;

    @Builder
    public Comment(Post post,
                   Member member,
                   String comment) {

        this.post = post;
        this.member = member;
        this.comment = comment;
    }

    public static Comment toCommentEntity(Post post,
                                          Member member,
                                          CommentReqDTO.CREATE create) {

        return Comment.builder()
                .post(post)
                .member(member)
                .comment(create.getComment())
                .build();
    }

    public void updateComment(CommentReqDTO.UPDATE update) {

        this.comment = update.getComment();
    }

    public void markAsDeleted() {

        this.status = DeleteStatus.DELETED;
    }
}
