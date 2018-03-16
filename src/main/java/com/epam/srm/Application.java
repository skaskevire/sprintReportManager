package com.epam.srm;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(Application.class, args);
	}
}