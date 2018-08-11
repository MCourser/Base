package com.machao.base.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitMqConfig {
	
	@Bean
	public ExponentialBackOffPolicy exponentialBackOffPolicy() {
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(500);
		backOffPolicy.setMaxInterval(10000);
		return backOffPolicy;
	}
	
	@Bean
	public RetryTemplate retryTemplate(ExponentialBackOffPolicy exponentialBackOffPolicy) {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);
		return retryTemplate;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(RetryTemplate retryTemplate) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(new CachingConnectionFactory());
		rabbitTemplate.setRetryTemplate(retryTemplate);// 设置connect失败之后的策略
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		return rabbitTemplate;
	}

}
