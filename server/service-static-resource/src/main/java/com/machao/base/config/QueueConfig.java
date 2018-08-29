package com.machao.base.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.machao.base.model.mq.QueueName;

@Configuration
public class QueueConfig {

	@Bean
	public Queue imageResizingQueue() {
		return new Queue(QueueName.ImageResizing);
	}

	@Bean
	public Queue imageDeleteQueue() {
		return new Queue(QueueName.ImageDelete);
	}
	
	@Bean
	public Queue audioConvertQueue() {
		return new Queue(QueueName.AudioConvert);
	}
	
	@Bean
	public Queue audioDeleteQueue() {
		return new Queue(QueueName.AudioDelete);
	}
	
	@Bean
	public Queue audioPlayListQueue() {
		return new Queue(QueueName.AudioPlayList);
	}
	
	@Bean
	public Queue videoConvertQueue() {
		return new Queue(QueueName.VideoConvert);
	}
	
	@Bean
	public Queue videoDeleteQueue() {
		return new Queue(QueueName.VideoDelete);
	}
	
	@Bean
	public Queue videoPlayListQueue() {
		return new Queue(QueueName.VideoPlayList);
	}
	
	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("directExchange");
	}

	@Bean
	public Binding bindImageResizingQueue(Queue imageResizingQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(imageResizingQueue).to(directExchange).withQueueName();
	}
	
	@Bean
	public Binding bindImageDeleteQueue(Queue imageDeleteQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(imageDeleteQueue).to(directExchange).withQueueName();
	}
	
	@Bean
	public Binding bindAudioConvertQueue(Queue audioConvertQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(audioConvertQueue).to(directExchange).withQueueName();
	}
	
	@Bean
	public Binding bindAudioDeleteQueue(Queue audioDeleteQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(audioDeleteQueue).to(directExchange).withQueueName();
	}	
	
	@Bean
	public Binding bindAudioPlayListQueue(Queue audioPlayListQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(audioPlayListQueue).to(directExchange).withQueueName();
	}
	
	@Bean
	public Binding bindVideoConvertQueue(Queue videoConvertQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(videoConvertQueue).to(directExchange).withQueueName();
	}
	
	@Bean
	public Binding bindVideoDeleteQueue(Queue videoDeleteQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(videoDeleteQueue).to(directExchange).withQueueName();
	}	
	
	@Bean
	public Binding bindVideoPlayListQueue(Queue videoPlayListQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(videoPlayListQueue).to(directExchange).withQueueName();
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

}
