package com.playdata.domain.comment.entity;

import com.playdata.config.BaseEntity;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comment")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Article article;
    @ManyToOne
    private Member member;

    @Builder
    public Comment(String content, Article article) {
        this.content = content;
        this.article = article;
    }

    public void update(String content) {
        this.content = content;
    }
}
