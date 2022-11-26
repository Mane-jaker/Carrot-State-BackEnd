package com.example.carrotstatebackend.exceptions;

public class NotValidFormatException extends RuntimeException {

    public NotValidFormatException(String acceptedFormat){
        super("Invalid format provided, it can be:" + acceptedFormat);
    }
}
