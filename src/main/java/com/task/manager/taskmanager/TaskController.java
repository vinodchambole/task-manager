package com.task.manager.taskmanager;

import com.task.manager.taskmanager.service.TaskService;
import com.task.manager.taskmanager.service.dto.TaskDto;
import com.task.manager.taskmanager.service.dto.TaskResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Value("${host}")
    private String host;

    @PostMapping
    public ResponseEntity<TaskResource> createTask(@RequestBody TaskDto taskDto) {
        TaskResource task = taskService.createTask(taskDto);
        return ResponseEntity.created(createURI(task)).body(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskResource>> getAllTasks() {
        List<TaskResource> allTasks = taskService.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResource> getTask(@PathVariable Long id) {
        return taskService.getTask(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private URI createURI(TaskResource task) {
        return URI.create(host + "/tasks/" + task.getTaskId());
    }

}
