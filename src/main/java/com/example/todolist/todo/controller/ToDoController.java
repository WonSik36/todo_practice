package com.example.todolist.todo.controller;

import com.example.todolist.todo.dto.ToDoDto;
import com.example.todolist.todo.service.ToDoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping("/")
    public ResponseEntity<ToDoDto> save(@RequestBody ToDoDto toDoDto) {
        log.info("{}", toDoDto);

        ToDoDto res = toDoService.insertToDo(toDoDto);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ToDoDto>> getAll(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset) {
        List<ToDoDto> list = toDoService.selectAllToDoPage(offset);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoDto> get(@PathVariable("id") int id) {
        ToDoDto toDoDto = toDoService.selectToDo(id);

        return new ResponseEntity<>(toDoDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoDto> update(@PathVariable("id") int id, @RequestBody ToDoDto toDoDto) {
        toDoDto.setId(id);

        ToDoDto res = toDoService.updateToDo(toDoDto);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        toDoService.deleteToDo(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
