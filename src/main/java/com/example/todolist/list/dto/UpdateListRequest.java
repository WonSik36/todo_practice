package com.example.todolist.list.dto;

import com.example.todolist.list.entity.ToDoList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateListRequest {
    @NotNull
    private Integer id;

    @NotEmpty
    private String description;

    public ToDoList toEntity() {
        return ToDoList.builder()
                .description(description)
                .build();
    }
}
