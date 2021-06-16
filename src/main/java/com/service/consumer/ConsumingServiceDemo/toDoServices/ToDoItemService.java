package com.service.consumer.ConsumingServiceDemo.toDoServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.consumer.ConsumingServiceDemo.exception.ToDoItemServiceException;
import com.service.consumer.ConsumingServiceDemo.models.ToDoItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ToDoItemService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${todoitem.service.baseurl}")
    private String serviceUrl;

    public Optional<ToDoItem> consumeItem(int id) {
        try {
            // RestTemplate restTemplate = new RestTemplate();
            ToDoItem itemByObject = restTemplate.getForObject("http://localhost:4040/v1/ToDoItem/" + id, ToDoItem.class);
            ResponseEntity<ToDoItem> itemByEntity = restTemplate.getForEntity("http://localhost:4040/v1/ToDoItem/" + id, ToDoItem.class);
            log.info(MessageFormat.format("Item entity response code: {0}, item entity res body: {1}", itemByEntity.getStatusCode(), itemByEntity.getBody()));
            return Optional.of(itemByObject);
        } catch (RestClientException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.OK, "requested to do item is not available.");
        }
        // return Optional.empty();
    }

    public Optional<List<ToDoItem>> consumeAllItems() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StringBuilder allItemUrl = new StringBuilder(serviceUrl + "/v1/ToDoItem/allItems");
            ParameterizedTypeReference<List<ToDoItem>> itemReferences = new ParameterizedTypeReference<List<ToDoItem>>() {};
            ResponseEntity<List<ToDoItem>> itemList = restTemplate.exchange(allItemUrl.toString(),
                    HttpMethod.GET,
                    null,
                    itemReferences);
            log.info(MessageFormat.format("Item entity response code: {0}, item entity res body: {1}", itemList.getStatusCode(), itemList.getBody().toString()));
            return Optional.of(itemList.getBody());
        } catch (RestClientException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new ToDoItemServiceException(e.getMessage());
        }
        // return Optional.empty();
    }

}
