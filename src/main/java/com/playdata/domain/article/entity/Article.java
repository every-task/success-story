package com.playdata.domain.article.entity;

import com.playdata.config.BaseEntity;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.member.entity.Member;
import com.playdata.domain.task.entity.Task;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Table(name = "article")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;
    private String thumbnailImageUrl;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @OneToMany(mappedBy = "article")
    private List<Task> tasks;

    @ManyToOne
    private Member member;

    @Builder
    public Article(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void update(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
    public void updateView(int view) {
        this.view = view;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
