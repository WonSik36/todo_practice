package com.example.todolist.list.service;

import com.example.todolist.list.dto.CreateListRequest;
import com.example.todolist.list.dto.ToDoListResponse;
import com.example.todolist.list.dto.UpdateListRequest;
import com.example.todolist.list.entity.ToDoList;
import com.example.todolist.list.repository.ToDoListRepository;
import com.example.todolist.todo.repository.ToDoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@Slf4j
public class ToDoListServiceTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private ToDoListService toDoListService;

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ToDoListRepository toDoListRepository;

    private List<ToDoList> listList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        toDoRepository.deleteAll();
        toDoListRepository.deleteAll();

        ToDoList t = ToDoList.builder().description("100").build();
        listList.add(toDoListRepository.save(t));

        t = ToDoList.builder().description("200").build();
        listList.add(toDoListRepository.save(t));

        t = ToDoList.builder().description("300").build();
        listList.add(toDoListRepository.save(t));

        t = ToDoList.builder().description("400").build();
        listList.add(toDoListRepository.save(t));

        t = ToDoList.builder().description("500").build();
        listList.add(toDoListRepository.save(t));

        toDoListRepository.flush();
        em.clear();
    }

    @Test
    public void createToDoListTest() {
        CreateListRequest req = new CreateListRequest();
        req.setDescription("1000");

        ToDoListResponse res = toDoListService.createToDoList(req);
        assertEquals(res.getDescription(), req.getDescription());

        List<ToDoList> lists = toDoListRepository.findAll();

        ToDoList ret = lists.get(lists.size() - 1);
        assertEquals(ret.getDescription(), req.getDescription());
        assertEquals(ret.getId(), res.getId());
    }

    @Test
    public void readToDoListTest() {
        for (ToDoList t : listList) {
            ToDoListResponse r = toDoListService.readToDoList(t.getId());
            compareToDoList(t, r);
        }
    }

    @Test
    public void readNotFoundTest() {
        assertThrows(NoSuchElementException.class,
                () -> toDoListService.readToDoList(999999));
    }

    @Test
    public void updateToDoListTest() {
        ToDoList target = listList.get(0);

        UpdateListRequest updateRequest = new UpdateListRequest();
        updateRequest.setId(target.getId());
        updateRequest.setDescription("updated");

        ToDoListResponse res = toDoListService.updateToDoList(updateRequest);
        assertEquals(res.getId(), updateRequest.getId());
        assertEquals(res.getDescription(), updateRequest.getDescription());

        res = toDoListService.readToDoList(updateRequest.getId());
        assertEquals(res.getId(), updateRequest.getId());
        assertEquals(res.getDescription(), updateRequest.getDescription());
    }

    @Test
    public void deleteToDoListTest() {
        ToDoList target = listList.get(0);

        toDoListService.deleteToDoList(target.getId());

        long count = toDoListRepository.count();
        assertEquals(count, 4);
    }

    private void compareToDoList(ToDoList t, ToDoListResponse r) {
        assertEquals(t.getId(), r.getId());
        assertEquals(t.getDescription(), r.getDescription());
    }
}