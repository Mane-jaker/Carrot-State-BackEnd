package com.example.carrotstatebackend.controllers.advices;

import com.example.carrotstatebackend.controllers.dtos.response.BaseResponse;
import com.example.carrotstatebackend.controllers.dtos.response.GetLoginResponse;
import com.example.carrotstatebackend.controllers.exceptions.LoginInvalidException;
import com.example.carrotstatebackend.controllers.exceptions.NotFoundException;
import com.example.carrotstatebackend.controllers.exceptions.NotValidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExeptionHandleFactory {

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
    private ResponseEntity<BaseResponse> handleNotFoundException(LoginInvalidException exception){
        GetLoginResponse response = new GetLoginResponse();
        response.setSuccess(false);
        BaseResponse errorResponse = BaseResponse.builder()
                .data(response)
                .message(exception.getLocalizedMessage())
                .success(false)
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
