package com.service.consumer.ConsumingServiceDemo.utils;

import com.service.consumer.ConsumingServiceDemo.exception.ToDoItemServiceException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ToDoItemExceptionHandler {
    @ExceptionHandler({ToDoItemServiceException.class, NullPointerException.class})
    public ResponseEntity<Object> exception(ToDoItemServiceException exception){
        System.out.println("logging...");
        return new ResponseEntity<>("To do item service currently has encountered some issues serving requests.", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
