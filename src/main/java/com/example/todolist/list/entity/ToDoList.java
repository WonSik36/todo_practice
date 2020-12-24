package com.example.todolist.list.entity;

import com.example.todolist.common.entity.BaseEntity;
import com.example.todolist.todo.entity.ToDo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ToDoList extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "list")
    private final List<ToDo> toDos = new ArrayList<>();

    @Builder
    public ToDoList(Integer id, String description) {
        this.id = id;
        this.description = description;
    }
}
