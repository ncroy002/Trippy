package com.trippy.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.logging.Logger;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class BackApplication {

	public static Logger LOGGER = Logger.getLogger(BackApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

}
