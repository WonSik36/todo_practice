package com.example.todolist.list.controller;

import com.example.todolist.list.dto.CreateListRequest;
import com.example.todolist.list.dto.ToDoListResponse;
import com.example.todolist.list.service.ToDoListService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/todo")
public class ToDoListController {
    private final ToDoListService service;

    @PostMapping("/")
    public ResponseEntity<ToDoListResponse> createToDoList(@RequestBody CreateListRequest req) {
        ToDoListResponse res = service.createToDoList(req);

        return ResponseEntity.ok().body(res);
    }

    @GetMapping("{id}")
    public ResponseEntity<ToDoListResponse> readToDoList(@PathVariable("id") int id) {
        ToDoListResponse res = service.readToDoList(id);

        return ResponseEntity.ok().body(res);
    }
}
