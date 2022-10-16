package com.gk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gk.beans.ProducerRequest;
import com.gk.beans.ProducerResponse;
import com.gk.service.DemoProducer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("v1")
public class ProducerController {
	
	@Autowired
	private DemoProducer producer;
	
	@PostMapping(value="/publish")
	public ResponseEntity<ProducerResponse> publishMessage(@RequestBody ProducerRequest request){
		log.info("Sending message");
		try {
			return new ResponseEntity<>(producer.sendMessage(request.getMessage()), HttpStatus.OK);
		}catch(Exception ex) {
			log.error("Failed", ex);
			return new ResponseEntity<>(ProducerResponse.builder().errorMessage(ex.getMessage()).build(), HttpStatus.OK);
		}
	}
}
