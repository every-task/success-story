package com.playdata.domain.task.request;

import com.playdata.domain.task.entity.Period;

public record TaskUpdateRequest(String content, Period period) {
}
