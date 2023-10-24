package com.playdata.domain.task.dto;

import com.playdata.domain.task.entity.Period;
import com.playdata.domain.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
