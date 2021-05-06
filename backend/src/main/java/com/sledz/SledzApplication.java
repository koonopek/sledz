package com.sledz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SledzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SledzApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder () {
		return new BCryptPasswordEncoder();
	}

}