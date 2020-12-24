package com.example.todolist.list.repository;

import com.example.todolist.list.entity.ToDoList;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
    @Query("SELECT t FROM ToDoList t WHERE t.id=:id AND t.isActive=true")
    Optional<ToDoList> findOneById(@Param("id") int id);

    @Query("SELECT t FROM ToDoList t WHERE t.isActive=true ORDER BY t.id DESC")
    List<ToDoList> findAllPage(Pageable pageable);
}
