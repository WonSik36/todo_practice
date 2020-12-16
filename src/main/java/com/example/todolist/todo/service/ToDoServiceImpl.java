package com.example.todolist.todo.service;

import com.example.todolist.todo.dto.ToDoDto;
import com.example.todolist.todo.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class ToDoServiceImpl implements ToDoService {
    private ToDoRepository toDoRepository;

    @Transactional
    public void insertToDo(ToDoDto toDoDto) {
        toDoRepository.save(toDoDto.toEntity());
    }
}
