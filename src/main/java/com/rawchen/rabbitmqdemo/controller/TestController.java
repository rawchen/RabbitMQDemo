package com.rawchen.rabbitmqdemo.controller;

import com.rawchen.rabbitmqdemo.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author RawChen
 * @date 2023-06-29 11:40
 */
@Controller
public class TestController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    @ResponseBody
    public String send() {
        //使用rabbitTemplate发送消息
        String message = "这是一条消息...." + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        /**
         * 参数：
         * 1、交换机名称
         * 2、routingKey
         * 3、消息内容
         */
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM, "inform.sms", message);
        return "success";
    }
}
