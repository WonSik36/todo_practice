package com.example.todolist.todo.entity;

import com.example.todolist.common.entity.BaseEntity;
import com.example.todolist.list.entity.ToDoList;
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
    private Integer id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isDone;

    @Enumerated(EnumType.STRING)
    private Importance importance;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ToDoList list;

    @Builder
    public ToDo(Integer id, String content, boolean isDone, Importance importance) {
        this.id = id;
        this.content = content;
        this.isDone = isDone;
        this.importance = importance;
    }

    public void setToDoList(ToDoList list) {
        if(this.list != null)
            this.list.getToDos().remove(this);

        this.list = list;
        list.getToDos().add(this);
    }

    public void update(ToDo other) {
        this.content = other.getContent();
        this.isDone = other.isDone();
        this.importance = other.getImportance();
    }
}
