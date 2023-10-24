package com.playdata.domain.article.request;

import com.playdata.domain.article.dto.ArticleDto;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.task.dto.TaskDto;
import com.playdata.domain.task.entity.Task;

import java.util.List;

public record ArticleRequest(ArticleDto article,
                              List<TaskDto> tasks) {

    public Article toEntityArticle() {
        return Article.builder()
                .title(article.getTitle())
                .content(article().getContent())
                .build();
    }

    public List<Task> toEntityTasks(Article article) {
        return tasks.stream().map(t -> new Task(null, t.getContent(), t.getPeriod(),article)).toList();
    }
}