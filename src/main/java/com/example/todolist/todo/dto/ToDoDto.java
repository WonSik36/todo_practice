package com.example.todolist.todo.dto;

import com.example.todolist.todo.entity.ToDo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ToDoDto {
    private int id;

    private String content;

    @JsonProperty("isDone")
    private boolean isDone;

    @Builder
    public ToDoDto(int id, String content, boolean isDone) {
        this.id = id;
        this.content = content;
        this.isDone = isDone;
    }

    public ToDo toEntity() {
        return ToDo.builder().id(id).content(content).isDone(isDone).build();
    }

    public static ToDoDto of(ToDo entity) {
        return new ToDoDto(entity.getId(), entity.getContent(), entity.isDone());
    }
}
