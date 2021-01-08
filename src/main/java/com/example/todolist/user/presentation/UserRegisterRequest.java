package com.example.todolist.user.presentation;

import com.example.todolist.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class UserRegisterRequest {
    private String id;
    private String username;

    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .build();
    }
}
