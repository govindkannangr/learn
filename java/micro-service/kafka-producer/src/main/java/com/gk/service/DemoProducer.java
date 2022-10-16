package com.gk.service;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.gk.beans.ProducerResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DemoProducer {
	@Autowired
	@Qualifier("demoProducerTemplate")
	private KafkaTemplate<String, String> kafkaTemplate;
	private String demoTopic="students";
	
	public ProducerResponse sendMessage(String message) throws InterruptedException, ExecutionException {
		ProducerRecord<String, String> producerRecord = new ProducerRecord<>(demoTopic, message);
		log.info("Published message: ", producerRecord);
		SendResult<String, String> result =  kafkaTemplate.send(producerRecord).get();
		ProducerResponse response = ProducerResponse.builder().topic(result.getRecordMetadata().topic())
				.partition(Integer.toString(result.getRecordMetadata().partition()))
				.offset(Long.toString(result.getRecordMetadata().offset()))
				.timestamp(Long.toString(result.getRecordMetadata().timestamp())).build();
		log.info("Status: ", response);
		return response;
	}
}
