package com.example.ex2_todo.service;

import com.example.ex2_todo.entity.Task;
import com.example.ex2_todo.exception.NotFoundException;
import com.example.ex2_todo.repository.TaskRepository;
import com.example.ex2_todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;


    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));
    }
    @Override
    public List<Task> getTasksByUserId(int userId) {
        return taskRepository.findByAuthor(userService.getUserById(userId));
    }

    @Override
    public List<Task> getTasksByAuthorId(int id) {
        return taskRepository.findByAuthor(userService.getUserById(id));
    }

    @Override
    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

}
