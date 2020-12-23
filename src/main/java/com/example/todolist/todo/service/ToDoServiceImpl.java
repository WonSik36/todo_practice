package com.example.todolist.todo.service;

import com.example.todolist.todo.dto.CreateToDoRequest;
import com.example.todolist.todo.dto.ToDoResponse;
import com.example.todolist.todo.dto.UpdateToDoRequest;
import com.example.todolist.todo.entity.ToDo;
import com.example.todolist.todo.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
@Transactional
public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoResponse insertToDo(CreateToDoRequest toDoDto) {
        ToDo toDo = toDoRepository.save(toDoDto.toEntity());

        return ToDoResponse.of(toDo);
    }

    public ToDoResponse selectToDo(int id) {
        return toDoRepository.findOneById(id)
                .map(ToDoResponse::of)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<ToDoResponse> selectAllToDoPage(int offset) {
        List<ToDo> toDos = toDoRepository.findAllPage(PageRequest.of(offset, 10));

        if(toDos.size() == 0)
            throw new NoSuchElementException();

        return toDos.stream().map(ToDoResponse::of).collect(toList());
    }

    public ToDoResponse updateToDo(UpdateToDoRequest toDoDto) {
        toDoRepository.findOneById(toDoDto.getId())
                .orElseThrow();

        ToDo toDo = toDoRepository.save(toDoDto.toEntity());

        return ToDoResponse.of(toDo);
    }

    public void deleteToDo(int id) {
        ToDo toDo = toDoRepository.findOneById(id)
                .orElseThrow();

        toDo.delete();
    }
}
