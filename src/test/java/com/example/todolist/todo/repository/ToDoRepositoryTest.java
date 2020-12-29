package com.example.todolist.todo.repository;

import com.example.todolist.list.entity.ToDoList;
import com.example.todolist.list.repository.ToDoListRepository;
import com.example.todolist.todo.entity.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class ToDoRepositoryTest {
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
    public void testWhereClause() {
        for(ToDo t : toDoList.getToDos()) {
            t.delete();
            toDoRepository.save(t);
        }

        List<ToDo> ret = toDoRepository.findAll();
        log.info("return value {}, expected value {}", ret.size(), 0);
        assertEquals(ret.size(), 0);

        long count = toDoRepository.count();
        log.info("total value {}, expected value {}", count, 0);
        assertEquals(count, 0);
    }
}