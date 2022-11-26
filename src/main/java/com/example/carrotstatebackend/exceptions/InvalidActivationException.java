package com.example.carrotstatebackend.exceptions;

public class InvalidActivationException extends RuntimeException{
    public InvalidActivationException(){
        super("Can't activate a real sate if this is activated now");
    }
}
