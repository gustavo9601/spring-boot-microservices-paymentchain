package com.paymentchain.businessdomain.transactions;

import com.paymentchain.businessdomain.transactions.clients.ExceptionFeignClientErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class TransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionsApplication.class, args);
	}

	/*
	* Bean que inicializa el manejador de errores para el cliente feign
	* */
	@Bean
	public ErrorDecoder errorDecoder() {
		return new ExceptionFeignClientErrorDecoder();
	}

}
