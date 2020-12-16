package com.example.todolist.todo.service;

import com.example.todolist.todo.dto.ToDoDto;
import com.example.todolist.todo.entity.ToDo;

public interface ToDoService {
    ToDoDto insertToDo(ToDoDto toDoDto);

    ToDoDto selectToDo(int id);
}
