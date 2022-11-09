package com.example.carrotstatebackend.controllers.exceptions;

public class LoginInvalidException extends RuntimeException{
    public LoginInvalidException(){
        super("the email or password are not correct");
    }
}
