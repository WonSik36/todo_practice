package com.example.todolist.list.service;

import com.example.todolist.list.dto.CreateListRequest;
import com.example.todolist.list.dto.ToDoListResponse;
import com.example.todolist.todo.dto.CreateToDoRequest;
import com.example.todolist.todo.dto.ToDoResponse;
import com.example.todolist.todo.dto.UpdateToDoRequest;

public interface ToDoListService {
    ToDoListResponse createToDoList(CreateListRequest dto);
    ToDoListResponse readToDoList(int id);

    ToDoResponse readToDo(int listId, int id);
    void createToDo(int listId, CreateToDoRequest createRequest);
    void updateToDo(int listId, UpdateToDoRequest updateRequest);
    void deleteToDo(int listId, int id);
}
