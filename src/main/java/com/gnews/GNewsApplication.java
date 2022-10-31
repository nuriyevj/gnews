package com.gnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:apps.properties")
public class GNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GNewsApplication.class, args);
	}

}
