package com.deeRav.exception;


import com.deeRav.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobleExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse>resourceNotFoundExceptionHandler(ResourceNotFound ex){
            String message =ex.getMessage();
            ApiResponse apiResponse  = new ApiResponse(message,false);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>handalMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> respo = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((errors)->{
            String fieldName = ((FieldError) errors).getField();
            String message = errors.getDefaultMessage();
            respo.put(fieldName,message);
        });


        return new ResponseEntity<>(respo,HttpStatus.BAD_REQUEST);

    }




}
