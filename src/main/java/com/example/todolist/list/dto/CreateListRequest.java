package com.example.todolist.list.dto;

import com.example.todolist.list.entity.ToDoList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreateListRequest {
    @NotEmpty(message = "must not be empty")
    private String description;

    public ToDoList toEntity() {
        return ToDoList.builder()
                .description(description)
                .build();
    }
}
