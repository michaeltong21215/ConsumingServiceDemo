package com.service.consumer.ConsumingServiceDemo.controller;

import com.service.consumer.ConsumingServiceDemo.exception.ToDoItemServiceException;
import com.service.consumer.ConsumingServiceDemo.models.ToDoItem;
import com.service.consumer.ConsumingServiceDemo.toDoServices.ToDoItemService;
import com.service.consumer.ConsumingServiceDemo.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/test")
public class ItemConsumptionController extends BaseController{

    @Autowired
    ToDoItemService toDoItemService;

    @GetMapping(value="/{id}")
    public ResponseEntity<Optional<ToDoItem>> getToDoItems(@PathVariable int id){
        Optional<ToDoItem> getItem = toDoItemService.consumeItem(id);
        if(getItem.isPresent()) {
            return new ResponseEntity<>(getItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.OK);
        }
    }

    @GetMapping(value="/allItems")
    public ResponseEntity<List<ToDoItem>> getAllItems(){
        Optional<List<ToDoItem>> getItems = toDoItemService.consumeAllItems();
        if(getItems.isPresent()) {
            return new ResponseEntity<>(getItems.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.OK);
        }
    }
    @ExceptionHandler({ToDoItemServiceException.class, NullPointerException.class})
    public ResponseEntity<Object> exception(ToDoItemServiceException exception){
        System.out.println("logging...");
        return new ResponseEntity<>("From Controller service currently has encountered some issues serving requests.", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
