package com.backend.PowerUp.exceptions;

public class WrongLoginOrPasswordException extends RuntimeException {
    private final int status;

    public WrongLoginOrPasswordException(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return String.format(status + " Неверный логин или пароль");
    }
}
