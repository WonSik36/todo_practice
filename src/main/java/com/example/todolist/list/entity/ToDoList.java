package com.example.todolist.list.entity;

import com.example.todolist.common.entity.BaseEntity;
import com.example.todolist.todo.dto.CreateToDoRequest;
import com.example.todolist.todo.dto.UpdateToDoRequest;
import com.example.todolist.todo.entity.ToDo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Where(clause = "is_active=1")
public class ToDoList extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ToDo> toDos = new ArrayList<>();

    @Builder
    public ToDoList(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public ToDo readToDo(Integer id) {
        return toDos.stream()
                .filter(t->t.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public void createToDo(CreateToDoRequest createRequest) {
        ToDo toDo = ToDo.create(createRequest);

        toDos.add(toDo);
        toDo.setToDoList(this);
    }

    public void updateToDo(UpdateToDoRequest updateRequest) {
        ToDo origin = this.readToDo(updateRequest.getId());

        origin.update(updateRequest);
    }

    public void deleteToDo(Integer id) {
        ToDo target = this.readToDo(id);

        target.delete();
    }
}
