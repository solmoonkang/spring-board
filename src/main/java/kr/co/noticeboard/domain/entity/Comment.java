package kr.co.noticeboard.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
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

    @Column(name = "comment", length = 100)
    private String comment;

    @Builder
    private Comment(Post post, String comment) {
        this.post = post;
        this.comment = comment;
    }
}
