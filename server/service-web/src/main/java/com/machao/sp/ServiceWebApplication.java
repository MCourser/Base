package com.machao.sp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableHystrix
@EnableFeignClients
@EnableSwagger2
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@SpringBootApplication 
public class ServiceWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceWebApplication.class, args);
	}
}


