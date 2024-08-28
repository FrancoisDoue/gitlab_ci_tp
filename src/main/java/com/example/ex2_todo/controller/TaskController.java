package com.example.ex2_todo.controller;

import com.example.ex2_todo.dto.TaskPostDTO;
import com.example.ex2_todo.entity.Task;
import com.example.ex2_todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

//    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(value = "id", required = false) Integer userId) {
        System.out.println("param: " + userId);
        List<Task> tasks = (userId != null && userId != 0) ? taskService.getTasksByUserId(userId) : taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskPostDTO taskPost) {
        return new ResponseEntity<>(
                taskService.saveTask(taskPost.toEntity()),
                HttpStatus.CREATED
        );
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody TaskPostDTO taskPost) {
        return ResponseEntity.ok(taskService.updateTask(taskPost.toEntity()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(name = "id") int id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<Task>> getTaskByAuthorId(@PathVariable(name = "id") int id) {
        List<Task> tasks = taskService.getTasksByAuthorId(id);
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") int id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
