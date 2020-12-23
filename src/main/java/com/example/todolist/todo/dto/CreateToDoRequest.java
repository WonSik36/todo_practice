package com.example.todolist.todo.dto;

import com.example.todolist.todo.entity.Importance;
import com.example.todolist.todo.entity.ToDo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateToDoRequest {
    private String content;

    @JsonProperty("isDone")
    private boolean isDone;

    private Importance importance;

    @Builder
    public CreateToDoRequest(String content, boolean isDone, Importance importance) {
        this.content = content;
        this.isDone = isDone;
        this.importance = importance;
    }

    public ToDo toEntity() {
        return ToDo.builder()
                .content(content)
                .isDone(isDone)
                .importance(importance)
                .build();
    }

    public static CreateToDoRequest of(ToDo entity) {
        return new CreateToDoRequest(entity.getContent(), entity.isDone(), entity.getImportance());
    }
}
