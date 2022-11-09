package com.example.carrotstatebackend.controllers.exceptions;

public class LoginInvalidException extends RuntimeException{
    public LoginInvalidException(){
        super("invalid: email or password");
    }
}
