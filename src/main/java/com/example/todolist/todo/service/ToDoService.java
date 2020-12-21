package com.example.todolist.todo.service;

import com.example.todolist.todo.dto.ToDoDto;

import java.util.List;

public interface ToDoService {
    ToDoDto insertToDo(ToDoDto toDoDto);

    ToDoDto selectToDo(int id);

    List<ToDoDto> selectAllToDoPage(int offset);
}
