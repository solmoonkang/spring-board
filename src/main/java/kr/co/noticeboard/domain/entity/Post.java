package kr.co.noticeboard.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "content", length = 500)
    private String content;

    @Builder
    public Post(Long id,
                String title,
                String name,
                String content) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.content = content;
    }
}
