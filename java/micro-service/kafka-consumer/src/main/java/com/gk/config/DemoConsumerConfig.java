package com.gk.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.ContainerType;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableKafka
public class DemoConsumerConfig {

	@Autowired
	private KafkaProperties kafkaProperties;
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory(){
		Map<String, Object> props = new HashMap<String, Object>(kafkaProperties.buildConsumerProperties());
		Map<String, Object> config = new HashMap<String, Object>(kafkaProperties.getConsumer().buildProperties());
		config.putAll(props);
		config.putIfAbsent(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		config.replace("key.deserializer", StringDeserializer.class.getName());
		config.replace("value.deserializer", StringDeserializer.class.getName());
		log.info("Consumer Properties: ");
		props.forEach((key,value)->log.info(key+": "+ value));
		return new DefaultKafkaConsumerFactory<String, String>(props);
	}
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> demoConsumerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
		factory.setStatefulRetry(true);
		factory.setErrorHandler(null);
		log.info("Consumer Properties: ");
		return factory;
	}
	
}
