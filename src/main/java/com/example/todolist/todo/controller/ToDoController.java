package com.example.todolist.todo.controller;

import com.example.todolist.todo.dto.*;
import com.example.todolist.todo.service.ToDoService;
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
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoService toDoService;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/")
    public ResponseEntity<ToDoResponse> save(@RequestBody CreateToDoRequest toDoDto) {
        log.info("{}", toDoDto);

        ToDoResponse res = toDoService.insertToDo(toDoDto);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ToDoResponse>> getAll(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset) {
        List<ToDoResponse> list = toDoService.selectAllToDoPage(offset);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoResponse> get(@PathVariable("id") int id) {
        ToDoResponse toDoDto = toDoService.selectToDo(id);

        return new ResponseEntity<>(toDoDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoResponse> update(@PathVariable("id") int id, @RequestBody @Valid UpdateToDoRequest toDoDto) {
        if (id != toDoDto.getId())
            throw new IllegalArgumentException("Resource Id is not match with body");

        ToDoResponse res = toDoService.updateToDo(toDoDto);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        toDoService.deleteToDo(id);

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
