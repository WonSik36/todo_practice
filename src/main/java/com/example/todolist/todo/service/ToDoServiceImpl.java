package com.example.todolist.todo.service;

import com.example.todolist.todo.dto.ToDoDto;
import com.example.todolist.todo.entity.ToDo;
import com.example.todolist.todo.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class ToDoServiceImpl implements ToDoService {
    private ToDoRepository toDoRepository;

    @Transactional
    public ToDoDto insertToDo(ToDoDto toDoDto) {
        ToDo toDo = toDoRepository.save(toDoDto.toEntity());

        return ToDoDto.of(toDo);
    }

    @Transactional
    public ToDoDto selectToDo(int id) {
        return toDoRepository.findOneByIsActive(id)
                .map(ToDoDto::of)
                .orElseThrow(NoSuchElementException::new);
    }
}
