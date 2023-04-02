package com.spring;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://github.com/bezkoder/spring-boot-download-csv-file

@SpringBootApplication
public class Application {

	private static final Logger logger = LogManager.getLogger(Application.class);
	
	public static void main(String[] args) {
		logger.info("--- Hello --- ");
		SpringApplication.run(Application.class, args);
	}
 
}
