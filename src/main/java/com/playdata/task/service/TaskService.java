package com.playdata.task.service;

import com.playdata.domain.task.repository.TaskRepository;
import com.playdata.domain.task.request.TaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public void save(TaskRequest taskRequest) {
        taskRepository.save(taskRequest.toEntity());
    }
}
