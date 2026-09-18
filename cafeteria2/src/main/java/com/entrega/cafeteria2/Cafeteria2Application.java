package com.entrega.cafeteria2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Cafeteria2Application {

	public static void main(String[] args) {
		SpringApplication.run(Cafeteria2Application.class, args);
	}

}
