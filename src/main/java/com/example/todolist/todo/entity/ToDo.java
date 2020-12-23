package com.example.todolist.todo.entity;

import com.example.todolist.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ToDo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isDone;

    @Enumerated(EnumType.STRING)
    private Importance importance;

    @Builder
    public ToDo(int id, String content, boolean isDone, Importance importance) {
        this.id = id;
        this.content = content;
        this.isDone = isDone;
        this.importance = importance;
    }
}
