package com.example.carrotstatebackend.exceptions;

public class InvalidStatusException extends RuntimeException{
    public InvalidStatusException(){
        super("invalid");
    }
}
