package com.service.consumer.ConsumingServiceDemo.utils;

import com.service.consumer.ConsumingServiceDemo.exception.ToDoItemServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {
    @ExceptionHandler({ToDoItemServiceException.class, NullPointerException.class})
    public ResponseEntity<Object> exception(ToDoItemServiceException exception){
        return new ResponseEntity<>("Base Controller To Do Item service has encountered some issues serving requests.", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
