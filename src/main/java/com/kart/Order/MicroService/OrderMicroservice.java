package com.kart.Order.MicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class OrderMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(OrderMicroservice.class, args);
	}


	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(){
		return  new RestTemplate();
	}

}
