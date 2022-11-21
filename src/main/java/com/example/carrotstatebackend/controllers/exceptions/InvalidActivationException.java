package com.example.carrotstatebackend.controllers.exceptions;

public class InvalidActivationException extends RuntimeException{
    public InvalidActivationException(){
        super("Can't activate a real sate if this is activated now");
    }
}
