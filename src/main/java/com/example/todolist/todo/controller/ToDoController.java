package com.example.todolist.todo.controller;

import com.example.todolist.todo.dto.ToDoDto;
import com.example.todolist.todo.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/todo")
public class ToDoController {
    private ToDoService toDoService;

    @PostMapping("/")
    public ResponseEntity<Map<String,Object>> save(ToDoDto toDoDto) {
        toDoService.insertToDo(toDoDto);

        return new ResponseEntity<>(ok(), HttpStatus.OK);
    }

    private Map<String, Object> ok() {
        Map<String, Object> map = new HashMap<>();

        map.put("Status Code", "200 Ok");

        return map;
    }
}
