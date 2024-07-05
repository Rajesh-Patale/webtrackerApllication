package com.webtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class WebTracker1Application {

	public static void main(String[] args) {
		SpringApplication app= new SpringApplication(WebTracker1Application.class);

		ConfigurableApplicationContext context = app.run(args);
		String localhostLink = "http://localhost:9090";
		System.out.println("Application is running at : "+localhostLink);
		
	}
	

}
