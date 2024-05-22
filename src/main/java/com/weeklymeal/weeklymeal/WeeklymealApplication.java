package com.weeklymeal.weeklymeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.weeklymeal.weeklymeal")
public class WeeklymealApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeeklymealApplication.class, args);
	}

}
