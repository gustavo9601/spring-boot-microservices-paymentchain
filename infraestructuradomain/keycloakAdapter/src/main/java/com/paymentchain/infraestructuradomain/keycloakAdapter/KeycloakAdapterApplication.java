package com.paymentchain.infraestructuradomain.keycloakAdapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class KeycloakAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakAdapterApplication.class, args);
	}

}
