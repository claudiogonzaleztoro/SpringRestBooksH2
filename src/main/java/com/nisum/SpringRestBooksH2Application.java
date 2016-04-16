package com.nisum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebServlet;

@SpringBootApplication
public class SpringRestBooksH2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestBooksH2Application.class, args);
	}

}
