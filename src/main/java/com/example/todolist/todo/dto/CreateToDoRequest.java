package com.example.todolist.todo.dto;

import com.example.todolist.todo.entity.Importance;
import com.example.todolist.todo.entity.ToDo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateToDoRequest {
    @NotEmpty
    private Integer listId;

    private String content;

    @JsonProperty("isDone")
    private boolean isDone;

    private Importance importance;

    @Builder
    public CreateToDoRequest(Integer listId, String content, boolean isDone, Importance importance) {
        this.listId = listId;
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
}
