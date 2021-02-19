package com.activemq.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/test")
public class TestController {

	private final JmsTemplate jmsTemplate;
	
	@GetMapping("{message}")
	public String publish(@PathVariable final String message) {
		jmsTemplate.convertAndSend(message);
		return "Done";
	}
	
	
	@JmsListener(destination = "${spring.jms.template.default-destination}")
	private void subscriber1(final String message) {
		log.info("Subscriber1 ==> {}",message);
	}
	
	@JmsListener(destination = "${spring.jms.template.default-destination}")
	private void subscriber2(final String message) {
		log.info("Subscriber2 ==> {}",message);
	}
	
	@JmsListener(destination = "${spring.jms.template.default-destination}")
	private void subscriber3(final String message) {
		log.info("Subscriber3 ==> {}",message);
	}
	

}
