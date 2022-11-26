package com.example.carrotstatebackend.exceptions;

public class InvalidDeleteException extends RuntimeException{
    public InvalidDeleteException(){
        super("can't delete a property with an owner");
    }
}
