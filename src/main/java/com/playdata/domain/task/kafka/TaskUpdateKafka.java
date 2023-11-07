package com.playdata.domain.task.kafka;

import com.playdata.domain.task.entity.Period;
import com.playdata.domain.task.entity.Task;

import java.util.UUID;

public record TaskUpdateKafka(UUID id, String content, Period period) {
    public static TaskUpdateKafka of(Task task){
        return new TaskUpdateKafka(task.getId(), task.getContent(), task.getPeriod());
    }
}
