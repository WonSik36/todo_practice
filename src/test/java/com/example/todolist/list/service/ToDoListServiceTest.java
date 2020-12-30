package com.example.todolist.list.service;

import com.example.todolist.list.dto.CreateListRequest;
import com.example.todolist.list.dto.ToDoListResponse;
import com.example.todolist.list.entity.ToDoList;
import com.example.todolist.list.repository.ToDoListRepository;
import com.example.todolist.todo.repository.ToDoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@Slf4j
public class ToDoListServiceTest {
    @Autowired
    private ToDoListService toDoListService;

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ToDoListRepository toDoListRepository;

    List<ToDoList> toDoListList;

    @BeforeEach
    public void setUp() {
        toDoRepository.deleteAll();
        toDoListRepository.deleteAll();

        toDoListList = new ArrayList<>();
        toDoListList.add(ToDoList.builder().id(1).description("100").build());
        toDoListList.add(ToDoList.builder().id(2).description("200").build());
        toDoListList.add(ToDoList.builder().id(3).description("300").build());
        toDoListList.add(ToDoList.builder().id(4).description("400").build());
        toDoListList.add(ToDoList.builder().id(5).description("500").build());
    }

    @Test
    public void createToDoList() {
        CreateListRequest req = new CreateListRequest();
        req.setDescription("1000");

        ToDoListResponse res = toDoListService.createToDoList(req);
        assertEquals(res.getDescription(), "1000");

        List<ToDoList> lists = toDoListRepository.findAll();
        assertEquals(lists.size(), 1);

        ToDoList ret = lists.get(0);
        assertEquals(ret.getDescription(), "1000");
        assertEquals(ret.getId(), res.getId());
    }

}