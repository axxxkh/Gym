package org.example.clientService.exceptions;

public class UserNotFound extends Exception {
    public UserNotFound(String message) {
        super(message);
    }
}
