package com.eidiko.niranjana.publisher;

import java.util.UUID;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eidiko.niranjana.dto.Order;
import com.eidiko.niranjana.dto.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

	@Autowired
	private RabbitTemplate template;
	
	@Value("${QUEUE}")
	private String queue;
	
	@Value("${TOPICEXCHANGE}")
	private String topicExchange;
	
	@Value("${ROUTINGKEY}")
	private String routingKey;
	
	@PostMapping("/{restaurantname}")
	public String foodOrder(@RequestBody Order order, @PathVariable String restaurantname)
	{
		order.setOrderId(UUID.randomUUID().toString());//ID generated automatically
		//payment service
		OrderStatus status = new OrderStatus(order,"process","order placed successfully in "+restaurantname);
		template.convertAndSend(topicExchange,routingKey,status);
		return "Success !!";
	}
}
