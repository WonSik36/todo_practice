package com.example.todolist.todo.dto;

import com.example.todolist.todo.entity.Importance;
import com.example.todolist.todo.entity.ToDo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateToDoRequest {
    @NotNull(message = "must not be null")
    private Integer id;

    private String content;

    @JsonProperty("isDone")
    private boolean isDone;

    private Importance importance;

    @Builder
    public UpdateToDoRequest(int id, String content, boolean isDone, Importance importance) {
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

    public static UpdateToDoRequest of(ToDo entity) {
        return new UpdateToDoRequest(entity.getId(), entity.getContent(), entity.isDone(), entity.getImportance());
    }
}
