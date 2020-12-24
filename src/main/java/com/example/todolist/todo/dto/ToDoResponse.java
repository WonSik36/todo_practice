package com.example.todolist.todo.dto;

import com.example.todolist.todo.entity.Importance;
import com.example.todolist.todo.entity.ToDo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ToDoResponse {
    private Integer id;

    private String content;

    @JsonProperty("isDone")
    private boolean isDone;

    private Importance importance;

    @Builder
    public ToDoResponse(int id, String content, boolean isDone, Importance importance) {
        this.id = id;
        this.content = content;
        this.isDone = isDone;
        this.importance = importance;
    }

    public ToDo toEntity() {
        return ToDo.builder()
                .id(id)
                .content(content)
                .isDone(isDone)
                .importance(importance)
                .build();
    }

    public static ToDoResponse of(ToDo entity) {
        return new ToDoResponse(entity.getId(), entity.getContent(), entity.isDone(), entity.getImportance());
    }
}
