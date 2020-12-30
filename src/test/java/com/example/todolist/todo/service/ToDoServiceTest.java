package com.example.todolist.todo.service;

import com.example.todolist.list.dto.ToDoListResponse;
import com.example.todolist.list.entity.ToDoList;
import com.example.todolist.list.repository.ToDoListRepository;
import com.example.todolist.list.service.ToDoListService;
import com.example.todolist.todo.dto.CreateToDoRequest;
import com.example.todolist.todo.dto.ToDoResponse;
import com.example.todolist.todo.dto.UpdateToDoRequest;
import com.example.todolist.todo.entity.Importance;
import com.example.todolist.todo.entity.ToDo;
import com.example.todolist.todo.repository.ToDoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@Slf4j
class ToDoServiceTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ToDoListService toDoListService;

    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private ToDoRepository toDoRepository;

    private ToDoList toDoList;

    @BeforeEach
    public void setUp() {
        toDoRepository.deleteAll();
        toDoListRepository.deleteAll();

        toDoList = ToDoList.builder().description("first one").build();

        ToDo t1 = ToDo.builder().content("100").isDone(false).build();
        t1.setToDoList(toDoList);

        ToDo t2 = ToDo.builder().content("200").isDone(false).build();
        t2.setToDoList(toDoList);

        ToDo t3 = ToDo.builder().content("300").isDone(false).build();
        t3.setToDoList(toDoList);

        ToDo t4 = ToDo.builder().content("400").isDone(false).build();
        t4.setToDoList(toDoList);

        ToDo t5 = ToDo.builder().content("500").isDone(false).build();
        t5.setToDoList(toDoList);

        toDoList = toDoListRepository.save(toDoList);
    }

    @Test
    void createToDoTest() {
        CreateToDoRequest req = CreateToDoRequest.builder()
                .content("123")
                .importance(Importance.LOW)
                .isDone(true)
                .build();

        toDoListService.createToDo(toDoList.getId(), req);

        ToDoListResponse res = toDoListService.readToDoList(toDoList.getId());
        ToDoResponse toDoResponse = res.getToDos().get(res.getToDos().size() - 1);

        assertEquals(toDoResponse.getContent(), req.getContent());
        assertEquals(toDoResponse.getImportance(), req.getImportance());
        assertEquals(toDoResponse.isDone(), req.isDone());
    }

    @Test
    void readToDoTest() {
        ToDoListResponse res = toDoListService.readToDoList(toDoList.getId());
        ToDoResponse origin = res.getToDos().get(0);
        ToDoResponse target = toDoListService.readToDo(toDoList.getId(), origin.getId());

        assertEquals(origin.getId(), target.getId());
        assertEquals(origin.getContent(), target.getContent());
        assertEquals(origin.getImportance(), target.getImportance());
        assertEquals(origin.isDone(), target.isDone());
    }

    @Test
    void readNotFoundToDoTest() {
        NoSuchElementException e = assertThrows(NoSuchElementException.class,
                () -> toDoListService.readToDo(toDoList.getId(), 999999));
    }

    @Test
    void updateToDoTest() {
        ToDoListResponse res = toDoListService.readToDoList(toDoList.getId());
        ToDoResponse origin = res.getToDos().get(0);
        UpdateToDoRequest req = UpdateToDoRequest.builder()
                .id(origin.getId())
                .content("Updated")
                .importance(origin.getImportance())
                .isDone(false)
                .build();

        toDoListService.updateToDo(toDoList.getId(), req);
        ToDo toDo = toDoRepository.findById(req.getId())
                .orElseThrow();

        assertEquals(req.getId(), toDo.getId());
        assertEquals(req.getContent(), toDo.getContent());
        assertEquals(req.getImportance(), toDo.getImportance());
        assertEquals(req.isDone(), toDo.isDone());
    }

    @Test
    void deleteToDoTest() {
        for(ToDo t: toDoList.getToDos()) {
            t.delete();
        }

        toDoListRepository.flush();
        // to clear persistence object toDoList
        entityManager.clear();

        ToDoListResponse res = toDoListService.readToDoList(toDoList.getId());
        for(ToDoResponse r: res.getToDos()) {
            log.info("{}",r);
        }

        assertEquals(res.getToDos().size(), 0);
    }
}