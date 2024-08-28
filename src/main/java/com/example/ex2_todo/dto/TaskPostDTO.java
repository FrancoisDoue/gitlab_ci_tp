package com.example.ex2_todo.dto;

import com.example.ex2_todo.entity.Task;
import com.example.ex2_todo.entity.User;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaskPostDTO {
    private int id;
    private String title;
    private String description;
    private boolean isDone;
    @JsonAlias({"authorId", "author_id"})
    private int authorId;

    public Task toEntity() {
        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setDone(isDone);
        task.setAuthor(User.builder().id(authorId).build());
        return task;
    }
}
