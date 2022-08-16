package com.eidiko.niranjana.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.eidiko.niranjana.dto.OrderStatus;

@Component
public class OrderConsumer {
	public static final String QUEUE = "Msg_Queue";

	@RabbitListener(queues= QUEUE)
	public void consumeMssageFromQueue(OrderStatus orderStatus)
	{
		System.out.println("Message Received from Queue :"+orderStatus);
	}
}
