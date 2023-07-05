package com.rawchen.rabbitmqdemo.handler;

/**
 * @author RawChen
 * @date 2023-06-29 11:16
 */

import com.rabbitmq.client.Channel;
import com.rawchen.rabbitmqdemo.config.RabbitmqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveHandler {
	//监听sms队列
	@RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SMS})
	public void receive_sms(Object msg, Message message, Channel channel) {
//		System.out.println("QUEUE_INFORM_SMS msg: " + message.getBody());
		System.out.println("消息：" + new String(message.getBody()) + " 来自交换机：" + message.getMessageProperties().getReceivedExchange());

	}
}
