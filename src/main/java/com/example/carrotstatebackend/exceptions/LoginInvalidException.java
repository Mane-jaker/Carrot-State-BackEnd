package com.example.carrotstatebackend.exceptions;

public class LoginInvalidException extends RuntimeException{
    public LoginInvalidException(){
        super("the email or password are not correct");
    }
}
