package com.rawchen.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author RawChen
 * @date 2023-06-29 11:30
 */
@Configuration
public class RabbitmqConfig {

	public static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";
	public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
	public static final String ROUTINGKEY_SMS = "inform.#.sms.#";

	// 声明交换机
	@Bean(EXCHANGE_TOPICS_INFORM)
	public Exchange EXCHANGE_TOPICS_INFORM() {
		// durable(true) 持久化，mq重启之后交换机还在
		return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
	}

	// 声明QUEUE_INFORM_SMS队列
	@Bean(QUEUE_INFORM_SMS)
	public Queue QUEUE_INFORM_SMS() {
		return new Queue(QUEUE_INFORM_SMS);
	}

	// ROUTINGKEY_SMS队列绑定交换机，指定routingKey
	@Bean
	public Binding BINDING_ROUTINGKEY_SMS(@Qualifier(QUEUE_INFORM_SMS) Queue queue,
										  @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_SMS).noargs();
	}

}