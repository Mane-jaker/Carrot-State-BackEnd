package com.example.carrotstatebackend.controllers.exceptions;

public class NotValidFormatException extends RuntimeException {

    public NotValidFormatException(String acceptedFormat){
        super("Invalid format provided, it can be:" + acceptedFormat);
    }
}
