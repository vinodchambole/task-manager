package com.task.manager.taskmanager.service;

import com.task.manager.taskmanager.service.dto.TaskDto;
import com.task.manager.taskmanager.service.dto.TaskResource;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    TaskResource createTask(TaskDto taskDto);
    Optional<TaskResource> getTask(Long id);

    List<TaskResource> getAllTasks();
}
