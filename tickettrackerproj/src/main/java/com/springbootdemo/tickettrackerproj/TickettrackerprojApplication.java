package com.springbootdemo.tickettrackerproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springbootdemo.tickettrackerproj")
public class TickettrackerprojApplication {

	public static void main(String[] args) {
		SpringApplication.run(TickettrackerprojApplication.class, args);
	}

}
