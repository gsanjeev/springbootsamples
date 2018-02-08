package com.example.laxtech.actors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class ActorsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActorsApplication.class);

	@Bean
	CountDownLatch latch() {
		return new CountDownLatch(1);
	}

	public static void main(String[] args) throws InterruptedException {

		ApplicationContext ctx = SpringApplication.run(ActorsApplication.class, args);

		MessagePublisher publisher = (MessagePublisher)ctx.getBean("redisPublisher");
		CountDownLatch latch = ctx.getBean(CountDownLatch.class);

		LOGGER.info("Sending message...");
		Actor actor = new Actor("25", "Sanjeev Tripathi", 44);
		publisher.publish(actor);

		latch.await();

		System.exit(0);
	}

}
