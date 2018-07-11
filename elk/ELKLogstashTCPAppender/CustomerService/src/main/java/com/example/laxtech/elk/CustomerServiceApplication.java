package com.example.laxtech.elk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		System.out.println("CustomerServiceApplication: debug message slf4j");
		//SpringApplication.run(CustomerServiceApplication.class, args);
		SpringApplication app = new SpringApplication(CustomerServiceApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	private Logger logger = (Logger) LoggerFactory.getLogger(CustomerServiceApplication.class);

	/**
	 * Access spring variable using property
	 *
	 */
	@Value("${logging.spring-customer-service.file}")
	private String loggingFileName;

	public void run(String... arg0) throws Exception {
		int i = 0;
		while (true) {
			if (i++ > 2) {
				break;
			}
			logger.debug("debug message slf4j");
			logger.info("info message slf4j");
			logger.warn("warn message slf4j");
			logger.error("error message slf4j - log file name is {}", loggingFileName);
			System.out.println("CustomerServiceApplication: debug message slf4j  {}" + loggingFileName);

			Thread.sleep(1000);
		}

	}
}
