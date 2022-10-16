package com.gk.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DemoConsumer {

	@KafkaListener(topics="students", groupId="test2", clientIdPrefix = "", containerFactory = "demoConsumerFactory")
	public void consumerListner(@Payload String message, Acknowledgment acknowledgment) {
		log.info("Message Received: "+ message);
		acknowledgment.acknowledge();
	}
}
