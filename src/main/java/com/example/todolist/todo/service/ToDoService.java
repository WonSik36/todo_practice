package com.example.todolist.todo.service;

import com.example.todolist.todo.dto.CreateToDoRequest;
import com.example.todolist.todo.dto.ToDoResponse;
import com.example.todolist.todo.dto.UpdateToDoRequest;

import java.util.List;

public interface ToDoService {
    ToDoResponse insertToDo(CreateToDoRequest toDoDto);

    ToDoResponse selectToDo(int id);

    List<ToDoResponse> selectAllToDoPage(int offset);

    ToDoResponse updateToDo(UpdateToDoRequest toDoDto);

    void deleteToDo(int id);
}
