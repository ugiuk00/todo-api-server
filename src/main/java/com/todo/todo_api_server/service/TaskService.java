package com.todo.todo_api_server.service;

import com.todo.todo_api_server.constants.TaskStatus;
import com.todo.todo_api_server.model.Task;
import com.todo.todo_api_server.persist.TaskRepository;
import com.todo.todo_api_server.persist.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task add(String title, String description, LocalDate dueDate) {
        var e = TaskEntity.builder()
                .title(title)
                .description(description)
                .dueDate(Date.valueOf(dueDate))
                .status(TaskStatus.TODO)
                .build();

        var saved = this.taskRepository.save(e);
        return entityToObject(saved);
    }

    public List<Task> getAll() {
        return this.taskRepository.findAll().stream()
                .map(this::entityToObject)
                .collect(Collectors.toList());
    }

    public List<Task> getByDueDate(String dueDate) {
        return this.taskRepository.findAllByDueDate(Date.valueOf(dueDate)).stream()
                .map(this::entityToObject)
                .collect(Collectors.toList());
    }

    public List<Task> getByStatus(TaskStatus status) {
        return this.taskRepository.findAllByStatus(status).stream()
                .map(this::entityToObject)
                .collect(Collectors.toList());
    }

    public Task getOne(Long id) {
        var entity = this.getById(id);
        return this.entityToObject(entity);
    }

    private TaskEntity getById(Long id) {
        return this.taskRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("not exists task id [%d]", id)));
    }


    private Task entityToObject(TaskEntity e) {
        return Task.builder()
                .id(e.getId())
                .title(e.getTitle())
                .description(e.getDescription())
                .status(e.getStatus())
                .dueDate(e.getDueDate().toString())
                .createdAt(e.getCreatedAt().toLocalDateTime())
                .updatedAt(e.getUpdatedAt().toLocalDateTime())
                .build();
    }
}
