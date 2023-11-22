package com.playdata.domain.article.request;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.entity.Category;
import com.playdata.domain.member.entity.Member;
import com.playdata.domain.task.entity.Task;
import com.playdata.domain.task.request.TaskRequest;

import java.util.List;
import java.util.UUID;

public record ArticleRequest(List<TaskRequest> tasks, String title, String content, Category category,
                             String thumbnailImageUrl) {

    public Article toEntityArticle(UUID memberId) {
        return Article.builder()
                .title(title)
                .content(content)
                .category(category)
                .thumbnailImageUrl(thumbnailImageUrl)
                .member(Member.builder().id(memberId).build())
                .build();
    }

    public List<Task> toEntityTasks(Article article) {
        return tasks.stream()
                .map(taskRequest -> Task.builder()
                        .content(taskRequest.content())
                        .period(taskRequest.period())
                        .build())
                .toList();
    }
}