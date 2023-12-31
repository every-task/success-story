package com.playdata.task.service;

import com.playdata.domain.task.dto.TaskDto;
import com.playdata.domain.task.entity.Task;
import com.playdata.domain.task.kafka.TaskUpdateKafka;
import com.playdata.domain.task.repository.TaskRepository;
import com.playdata.domain.task.request.TaskUpdateRequest;
import com.playdata.domain.task.response.TaskResponse;
import com.playdata.kafka.TaskProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskProducer taskProducer;

    public List<TaskDto> taskSaveAll(List<Task> tasks) {
        return taskRepository.saveAll(tasks)
                .stream()
                .map(TaskDto::new)
                .toList();
        }

    public TaskResponse updateTask(UUID id, TaskUpdateRequest taskUpdateRequest) {
        Task task = findById(id);
        task.update(taskUpdateRequest.content(), taskUpdateRequest.period());
        TaskUpdateKafka taskUpdateKafka = TaskUpdateKafka.create(task);
        taskProducer.sendUpdate(taskUpdateKafka);
        return new TaskResponse(task);
    }

    public void deleteTask(UUID id) {
        Task task = findById(id);
        TaskUpdateKafka taskUpdateKafka = TaskUpdateKafka.create(task);
        task.delete();
        taskProducer.sendDelete(taskUpdateKafka);
    }

    private Task findById(UUID id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("No search id.id={%s}",id)));
    }
}
