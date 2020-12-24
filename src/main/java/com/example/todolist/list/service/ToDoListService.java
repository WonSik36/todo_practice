package com.example.todolist.list.service;

import com.example.todolist.list.dto.CreateListRequest;
import com.example.todolist.list.dto.ToDoListResponse;

public interface ToDoListService {
    ToDoListResponse createToDoList(CreateListRequest dto);
}
