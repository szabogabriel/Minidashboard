package com.github.szabogabriel.minidashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.github.szabogabriel.minidashboard"})
public class MinidashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinidashboardApplication.class, args);
	}

}
