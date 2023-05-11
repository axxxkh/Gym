package org.example.clientService.exceptions;

public class UserAlreadyExist extends Exception{
    public UserAlreadyExist (String message) {
        super(message);
    }
}
