package com.playdata.domain.article.request;

import com.playdata.domain.article.dto.ArticleDto;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.member.entity.Member;
import com.playdata.domain.task.dto.TaskDto;
import com.playdata.domain.task.entity.Task;

import java.util.List;
import java.util.UUID;

public record ArticleRequest(ArticleDto article,
                              List<TaskDto> tasks) {

    public Article toEntityArticle(UUID memberId) {
        return Article.builder()
                .title(article.getTitle())
                .content(article().getContent())
                .member(Member.builder().id(memberId).build())
                .build();
    }

    public List<Task> toEntityTasks(Article article) {
        return tasks.stream().map(t -> new Task(null, t.getContent(), t.getPeriod(),article)).toList();
    }
}