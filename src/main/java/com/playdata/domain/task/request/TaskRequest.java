package com.playdata.domain.task.request;

import com.playdata.domain.task.entity.Period;
import com.playdata.domain.task.entity.Task;

public record TaskRequest(String content, Period period) {
    public Task toEntity() {
        return Task.builder()
                .content(content)
                .period(period)
                .build();
    }
}
