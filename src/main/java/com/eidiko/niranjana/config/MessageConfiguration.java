package com.eidiko.niranjana.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
//	public static final String QUEUE = "Msg_Queue";
//	public static final String TOPICEXCHANGE = "Msg_TopicExchange";
//	public static final String ROUTINGKEY = "Queue_TopicExchange RoutingKey";
	
	@Value("${QUEUE}")
	private String queue;
	
	@Value("${TOPICEXCHANGE}")
	private String topicExchange;
	
	@Value("${ROUTINGKEY}")
	private String routingKey;
	
	@Bean
	public Queue queue()
	{
		return new Queue(queue);
	}
	
	@Bean
	public TopicExchange exchange()
	{
		return new TopicExchange(topicExchange);
	}
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange)
	{
		return BindingBuilder.bind(queue).to(exchange).with(routingKey); 
	}
	
	@Bean
	public MessageConverter converter()
	{
		return new Jackson2JsonMessageConverter();
	}
	@Bean
	public AmqpTemplate template(ConnectionFactory connectionFactory)
	{
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
	
}
