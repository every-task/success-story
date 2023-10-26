package com.playdata.task.service;

import com.playdata.domain.task.dto.TaskDto;
import com.playdata.domain.task.entity.Task;
import com.playdata.domain.task.repository.TaskRepository;
import com.playdata.domain.task.request.TaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public void save(TaskRequest taskRequest) {
        taskRepository.save(taskRequest.toEntity());
    }

    public List<TaskDto> taskSaveAll(List<Task> tasks) {
        return taskRepository.saveAll(tasks)
                .stream()
                .map(TaskDto::new)
                .toList();
        }
}
