package org.example.adminService.exceptions;

public class UserAlreadyExist extends Exception{
    public UserAlreadyExist (String message) {
        super(message);
    }
}
