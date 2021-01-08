package com.example.todolist.user.application;

import com.example.todolist.user.domain.InvalidPasswordException;
import com.example.todolist.user.domain.User;
import com.example.todolist.user.domain.UserRepository;
import com.example.todolist.user.presentation.UserDto;
import com.example.todolist.user.presentation.UserRegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void registerUser(UserRegisterRequest req) {
        User user = req.toEntity();

        userRepository.save(user);
    }

    public UserDto login(String userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow();

        if(passwordCheck(user.getPassword(), password))
            throw new InvalidPasswordException();

        return UserDto.of(user);
    }

    private boolean passwordCheck(String origin, String input) {
        return origin.equals(input);
    }
}
