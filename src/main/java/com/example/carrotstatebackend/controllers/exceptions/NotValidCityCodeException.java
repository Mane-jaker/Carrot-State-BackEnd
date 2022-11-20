package com.example.carrotstatebackend.controllers.exceptions;

public class NotValidCityCodeException extends RuntimeException{
    public NotValidCityCodeException(String code){
        super(code + " Is not a valid CityCode Exception");
    }
}
