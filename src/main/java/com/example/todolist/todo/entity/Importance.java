package com.example.todolist.todo.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Importance {
    VERY_LOW("very_low"),
    LOW("low"),
    NORMAL("normal"),
    HIGH("high"),
    VERY_HIGH("very_high");

    @JsonValue
    private String level;

    Importance(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return this.level;
    }

    @JsonCreator
    public static Importance fromText(String level) {
        switch (level) {
            case "very_low":
                return VERY_LOW;
            case "low":
                return LOW;
            case "normal":
                return NORMAL;
            case "high":
                return HIGH;
            case "very_high":
                return VERY_HIGH;
        }

        return null;
    }
}
