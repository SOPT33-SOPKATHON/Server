package com.sopt.sopkathonServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SopkathonServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SopkathonServerApplication.class, args);
	}

}
