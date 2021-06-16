package com.service.consumer.ConsumingServiceDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ConsumingServiceDemoApplication {

	public static void main(String[] args) {
		log.debug("running application...");
		SpringApplication.run(ConsumingServiceDemoApplication.class, args);
	}

}
