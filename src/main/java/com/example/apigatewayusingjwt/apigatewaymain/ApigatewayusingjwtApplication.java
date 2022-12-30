package com.example.apigatewayusingjwt.apigatewaymain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@SuppressWarnings("deprecation")
@EnableEurekaClient
public class ApigatewayusingjwtApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "application-eho"); //telling spring to use my application yaml
		SpringApplication.run(ApigatewayusingjwtApplication.class, args);
	}

	@Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
	
}
