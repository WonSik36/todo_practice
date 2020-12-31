package com.example.todolist.list.service;

import com.example.todolist.list.dto.CreateListRequest;
import com.example.todolist.list.dto.ToDoListResponse;
import com.example.todolist.list.dto.UpdateListRequest;
import com.example.todolist.list.entity.ToDoList;
import com.example.todolist.list.repository.ToDoListRepository;
import com.example.todolist.todo.dto.CreateToDoRequest;
import com.example.todolist.todo.dto.ToDoResponse;
import com.example.todolist.todo.dto.UpdateToDoRequest;
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

    @Override
    public ToDoListResponse readToDoList(int id) {
        return repository.findById(id)
                .map(ToDoListResponse::of)
                .orElseThrow();
    }

    @Override
    public ToDoListResponse updateToDoList(UpdateListRequest dto) {
        ToDoList res = repository.findById(dto.getId())
                .orElseThrow();

        res.update(dto);

        return ToDoListResponse.of(res);
    }

    @Override
    public void deleteToDoList(int id) {
        ToDoList res = repository.findById(id)
                .orElseThrow();

        res.delete();
    }

    @Override
    public ToDoResponse readToDo(int listId, int id) {
        ToDoList list = repository.findById(listId)
                .orElseThrow();

        return ToDoResponse.of(list.readToDo(id));
    }

    @Override
    public void createToDo(int listId, CreateToDoRequest createRequest) {
        ToDoList list = repository.findById(listId)
                .orElseThrow();

        list.createToDo(createRequest);

        repository.save(list);
    }

    @Override
    public void updateToDo(int listId, UpdateToDoRequest updateRequest) {
        ToDoList list = repository.findById(listId)
                .orElseThrow();

        list.updateToDo(updateRequest);
    }

    @Override
    public void deleteToDo(int listId, int id) {
        ToDoList list = repository.findById(listId)
                .orElseThrow();

        list.deleteToDo(id);
    }
}
