package com.example.hibernateTest;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class HibernateTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateTestApplication.class, args);
	}
}
