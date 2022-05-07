package com.task.manager.taskmanager.service;

import com.task.manager.taskmanager.repository.Task;
import com.task.manager.taskmanager.repository.TaskRepository;
import com.task.manager.taskmanager.service.dto.TaskDto;
import com.task.manager.taskmanager.service.dto.TaskResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskResource createTask(TaskDto taskDto) {
        Task savedTask = taskRepository.save(new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getStatus()));

        return convertTaskToTaskResource(savedTask);
    }



    @Override
    public Optional<TaskResource> getTask(Long id) {
        return taskRepository
                .findById(id)
                .map(this::convertTaskToTaskResource);
    }

    @Override
    public List<TaskResource> getAllTasks() {
        List<TaskResource> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(task -> tasks.add(convertTaskToTaskResource(task)));
        return tasks;
    }

    private TaskResource convertTaskToTaskResource(Task savedTask) {
        return new TaskResource(savedTask.getId(), savedTask.getTitle(), savedTask.getDescription(), savedTask.getStatus());
    }


}
