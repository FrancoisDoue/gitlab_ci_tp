package com.example.ex2_todo.repository;

import com.example.ex2_todo.entity.Task;
import com.example.ex2_todo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findByAuthor(User author);
}
