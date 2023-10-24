package com.playdata.domain.task.response;

import com.playdata.domain.article.dto.ArticleDto;
import com.playdata.domain.task.dto.TaskDto;
import com.playdata.domain.task.entity.Task;

public class TaskResponse extends TaskDto {
    private ArticleDto article;

    public TaskResponse(Task task) {
        super(task);
        article = new ArticleDto(task.getArticle());
    }
}
