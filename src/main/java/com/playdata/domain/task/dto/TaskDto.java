package com.playdata.domain.task.dto;

import com.playdata.domain.task.entity.Period;
import com.playdata.domain.task.entity.Task;

import java.util.UUID;

public class TaskDto {
    private UUID id;
    private String content;
    private Period period;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.content = task.getContent();
        this.period = task.getPeriod();
    }
}
