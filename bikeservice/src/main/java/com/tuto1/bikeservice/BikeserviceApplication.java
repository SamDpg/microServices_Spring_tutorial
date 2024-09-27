package com.tuto1.bikeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BikeserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeserviceApplication.class, args);
	}

}
