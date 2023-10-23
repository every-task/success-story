package com.playdata.task.controller;

import com.playdata.domain.task.request.TaskRequest;
import com.playdata.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/success")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public void save(@RequestBody TaskRequest taskRequest) {
        taskService.save(taskRequest);
    }
}
