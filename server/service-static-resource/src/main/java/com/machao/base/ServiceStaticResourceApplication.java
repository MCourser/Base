package com.machao.base;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.machao.base.service.StaticResourceService;

@EnableRabbit
@SpringBootApplication
public class ServiceStaticResourceApplication {
	
	@Autowired
	private StaticResourceService service;

	public static void main(String[] args) {
		SpringApplication.run(ServiceStaticResourceApplication.class, args);
	}
}
