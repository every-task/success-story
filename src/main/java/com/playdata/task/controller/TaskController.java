package com.playdata.task.controller;

import com.playdata.domain.task.kafka.TaskUpdateKafka;
import com.playdata.domain.task.request.TaskUpdateRequest;
import com.playdata.domain.task.response.TaskResponse;
import com.playdata.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/story")
public class TaskController {
    private final TaskService taskService;

    @PutMapping("/task/{id}")
    public TaskResponse updateTask(@PathVariable UUID id, @RequestBody TaskUpdateRequest taskUpdateRequest) {
        return taskService.updateTask(id, taskUpdateRequest);
    }
}
