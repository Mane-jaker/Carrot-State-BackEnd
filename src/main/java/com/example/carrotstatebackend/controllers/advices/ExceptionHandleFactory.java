package com.example.carrotstatebackend.controllers.advices;

import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetLoginResponse;
import com.example.carrotstatebackend.controllers.exceptions.*;
import com.example.carrotstatebackend.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandleFactory {

    @ExceptionHandler(NotValidFormatException.class)
    private ResponseEntity<BaseResponse> handleNotValidFormatException(NotValidFormatException exception){
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<BaseResponse> handleNotFoundException(NotFoundException exception){
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(LoginInvalidException.class)
    private ResponseEntity<BaseResponse> handleLoginInvalidException(LoginInvalidException exception){
        GetLoginResponse response = GetLoginResponse.builder().success(false).build();
        BaseResponse errorResponse = BaseResponse.builder()
                .data(response)
                .message(exception.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.PRECONDITION_FAILED)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(InvalidDeleteException.class)
    private ResponseEntity<BaseResponse> handleInvalidDeleteException(InvalidDeleteException exception){
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(InvalidActivationException.class)
    private ResponseEntity<BaseResponse> handleInvalidActivationException(InvalidActivationException exception){
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(NotValidCityCodeException.class)
    private ResponseEntity<BaseResponse> handleNotValidCityCodeException(NotValidCityCodeException exception){
        BaseResponse errorResponse = BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .success(false)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
