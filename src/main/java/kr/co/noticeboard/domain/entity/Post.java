package kr.co.noticeboard.domain.entity;

import jakarta.persistence.*;
import kr.co.noticeboard.domain.dto.request.PostReqDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_posts")
@AttributeOverride(
        name = "id",
        column = @Column(name = "post_id", length = 4)
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Column(name = "title", length = 50)
    private String title;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "content", length = 500)
    private String content;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String title,
                Member member,
                String content,
                List<Comment> comments) {
        this.title = title;
        this.member = member;
        this.content = content;
        this.comments = comments;
    }

    public static Post createPost(Member member, PostReqDTO.CREATE create) {
        return Post.builder()
                .title(create.getTitle())
                .member(member)
                .content(create.getContent())
                .build();
    }
}
