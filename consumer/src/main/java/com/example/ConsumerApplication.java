package com.example;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(InputInterface.class)
@SpringBootApplication
public class ConsumerApplication {

	Logger logger = Logger.getLogger(ConsumerApplication.class);
	
	@StreamListener("channel")
	public void log(Message<String> msg){
		logger.info("Received msg: " + msg.getPayload());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
}

interface InputInterface {
	
	@Input
	public SubscribableChannel channel();
}