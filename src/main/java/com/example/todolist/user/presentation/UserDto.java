package com.example.todolist.user.presentation;

import com.example.todolist.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private final Integer no;
    private final String id;
    private final String username;

    public static UserDto of(User user) {
        return new UserDto(user.getNo(), user.getId(), user.getUsername());
    }
}
