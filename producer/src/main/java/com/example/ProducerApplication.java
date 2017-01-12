package com.example;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(OutputInterface.class)
@SpringBootApplication
public class ProducerApplication {
	
	private Logger logger = Logger.getLogger(ProducerApplication.class);
	
	private MessageChannel mc;
	
	public ProducerApplication(OutputInterface oi) {
		// TODO Auto-generated constructor stub
		this.mc = oi.producerOutput();
	}
	
	@PostMapping(value="/greet/{name}")
	public void send(@PathVariable String name){
		String str = "Hello " + name;
		logger.info("Sending: " + str);
		Message<String> msg = MessageBuilder.withPayload(str).build();
		this.mc.send(msg);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
}

interface OutputInterface{
	
	@Output
	public MessageChannel producerOutput();
}
