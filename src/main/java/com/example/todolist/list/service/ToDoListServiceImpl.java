package com.example.todolist.list.service;

import com.example.todolist.list.dto.CreateListRequest;
import com.example.todolist.list.dto.ToDoListResponse;
import com.example.todolist.list.entity.ToDoList;
import com.example.todolist.list.repository.ToDoListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class ToDoListServiceImpl implements ToDoListService {
    private final ToDoListRepository repository;

    @Override
    public ToDoListResponse createToDoList(CreateListRequest dto) {
        ToDoList res = repository.save(dto.toEntity());

        return ToDoListResponse.of(res);
    }
}
