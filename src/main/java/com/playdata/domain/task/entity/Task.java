package com.playdata.domain.task.entity;

import com.playdata.config.BaseEntity;
import com.playdata.domain.article.entity.Article;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "task")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String content;
    @Enumerated(EnumType.STRING)
    private Period period;

    @ManyToOne
    private Article article;

    @Builder
    public Task(UUID id, String content, Period period,Article article) {
        this.id = id;
        this.content = content;
        this.period = period;
        this.article = article;
    }

}
