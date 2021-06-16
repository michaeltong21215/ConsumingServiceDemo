package com.service.consumer.ConsumingServiceDemo.models;

import lombok.Data;

@Data
public class ToDoItem {

    private Integer id;

    private String imageUrl;

    private String name;

    private String role;

    private String status;

    // private Users users;
}
