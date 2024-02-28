package com.backend.PowerUp.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    private final String username;

    public UserAlreadyExistException (String username) {
        this.username = username;
    }

    @Override
    public String getMessage() {
        return String.format("Пользователь с ником '%s' уже существует", username);
    }
}
