package com.example.todolist.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    public void updatePassword(String cur, String next) {
        if(!password.equals(cur))
            throw new IllegalStateException("Password is not equal");

        this.password = next;
    }

    @Builder
    public User(Integer no, String id, String password, String username) {
        this.no = no;
        this.id = id;
        this.password = password;
        this.username = username;
    }
}
