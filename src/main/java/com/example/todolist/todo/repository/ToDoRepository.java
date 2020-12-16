package com.example.todolist.todo.repository;

import com.example.todolist.todo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    @Query("SELECT t FROM ToDo t WHERE t.id=:id AND t.isActive=true")
    Optional<ToDo> findOneByIsActive(int id);
}
