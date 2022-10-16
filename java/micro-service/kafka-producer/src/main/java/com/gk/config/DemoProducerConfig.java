package com.gk.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DemoProducerConfig {
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@PostConstruct
	public void init() {
		log.info("Kafka Producer Properties: ", kafkaProperties.getProducer().buildProperties());
		log.info("Kafka Properties: ", kafkaProperties.getProperties());
		log.info("Kafka Bootstrap Servers: ",  kafkaProperties.getBootstrapServers());
	}
	
	@Bean(name = "demoProducerFactory")
	public ProducerFactory<String, String> demoProducerFactory(){
		Map<String, Object> config = new HashMap<String, Object>(kafkaProperties.getProperties());
		Map<String, Object> kafkaProps = new HashMap<String, Object>(kafkaProperties.getProducer().buildProperties());
		config.putAll(kafkaProps);
		config.putIfAbsent(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		log.info("Kafka Factory properties: ");
		config.forEach((key,value)->log.info(key+": "+value));
		return new DefaultKafkaProducerFactory<String, String>(config);
	}
	
	@Bean(name = "demoProducerTemplate")
	@DependsOn("demoProducerFactory")
	public KafkaTemplate<String, String> demoProducerTemplate(@Qualifier("demoProducerFactory") ProducerFactory<String, String> producerFactory){
		return new KafkaTemplate<String, String>(producerFactory, true);
	}
}
