package com.example.ex2_todo.service;

import com.example.ex2_todo.entity.Task;

import java.util.List;

public interface ITaskService {
    Task getTaskById(int id);
    List<Task> getTasksByAuthorId(int id);
    List<Task> getAllTasks();
    void deleteTaskById(int id);
    Task saveTask(Task task);
    Task updateTask(Task task);
    List<Task> getTasksByUserId(int id);

}
