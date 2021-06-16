package com.service.consumer.ConsumingServiceDemo.exception;

public class ToDoItemServiceException extends RuntimeException {
   public ToDoItemServiceException(String message){
       super(message);
   }
}
