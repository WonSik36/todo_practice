package com.example.todolist.list.dto;

import com.example.todolist.list.entity.ToDoList;
import com.example.todolist.todo.dto.ToDoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ToDoListResponse {
    private Integer id;

    private String description;

    private final List<ToDoResponse> toDos = new ArrayList<>();

    @JsonProperty("size")
    private int getSize() {
        return toDos.size();
    }

    @Builder
    public ToDoListResponse(Integer id, String description, List<ToDoResponse> toDos) {
        this.id = id;
        this.description = description;
        this.toDos.addAll(toDos);
    }

    public static ToDoListResponse of(ToDoList entity) {
        return ToDoListResponse.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .toDos(entity.getToDos()
                        .stream()
                        .map(ToDoResponse::of)
                        .collect(Collectors.toList()))
                .build();
    }
}
