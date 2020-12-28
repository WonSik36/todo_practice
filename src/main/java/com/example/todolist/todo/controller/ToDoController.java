package com.example.todolist.todo.controller;

import com.example.todolist.list.dto.ToDoListResponse;
import com.example.todolist.list.service.ToDoListService;
import com.example.todolist.todo.dto.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/todo/{listId}")
public class ToDoController {
    private final ToDoListService toDoListService;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/")
    public ResponseEntity<Void> save(@PathVariable("listId") int listId,
                                     @RequestBody CreateToDoRequest createRequest) {
        log.info("{}", listId);
        log.info("{}", createRequest);

        toDoListService.createToDo(listId, createRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<ToDoResponse>> getAll(@PathVariable("listId") int listId) {
        ToDoListResponse toDoList = toDoListService.readToDoList(listId);
        List<ToDoResponse> list = toDoList.getToDos();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoResponse> get(@PathVariable("listId") int listId, @PathVariable("id") int id) {
        ToDoResponse toDoDto = toDoListService.readToDo(listId, id);

        return ResponseEntity.ok().body(toDoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("listId") int listId,
                                               @PathVariable("id") int id,
                                               @RequestBody @Valid UpdateToDoRequest toDoDto) {
        if (id != toDoDto.getId())
            throw new IllegalArgumentException("Resource Id is not match with body");

        toDoListService.updateToDo(listId, toDoDto);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("listId") int listId, @PathVariable("id") int id) {
        toDoListService.deleteToDo(listId, id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/kafka/{msg}")
    public ResponseEntity<Void> kafkaTest(@PathVariable("msg") String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("topic001", msg);

        future.addCallback(
                new ListenableFutureCallback<>() {
                    @Override
                    public void onSuccess(SendResult<String, String> result) {
                        log.info("Sent message=[" + msg + "] with offset=[" + result.getRecordMetadata().offset() + "]");
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Unable to send message=[" + msg + "] due to : " + ex.getMessage());
                    }
                }
        );

        return ResponseEntity.ok().body(null);
    }
}
