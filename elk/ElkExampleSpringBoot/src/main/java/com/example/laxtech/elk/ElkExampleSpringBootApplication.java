package com.example.laxtech.elk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@SpringBootApplication
public class ElkExampleSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElkExampleSpringBootApplication.class, args);
	}
}

@RestController
class ELKController {
	private static final Logger logger = LoggerFactory.getLogger(ELKController.class.getName());

	@Autowired
	RestTemplate restTemplete;

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/elkdemo")
	public String helloWorld() {
		String response = "Hello user ! " + new Date();
        logger.info("/elkdemo - &gt; " + response);

		return response;
	}

	@RequestMapping(value = "/elk")
	public String helloWorld1() {

                //= restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
        ResponseEntity<String> response = restTemplete.getForEntity("http://localhost:8080/elkdemo",String.class);
		String responseString = response.getBody();
        logger.info("/elk - &gt; " + responseString);

		try {
            ResponseEntity<String> responseExp = restTemplete.getForEntity("http://localhost:8080/exception", String.class);
                    String responseExpStr = responseExp.getBody();
            logger.info("/elk trying to print exception - &gt; " + responseExpStr);
            responseString = responseString + " === " + responseExpStr;
		} catch (Exception e) {
			// exception should not reach here. Really bad practice :)
		}

		return responseString;
	}

	@RequestMapping(value = "/exception")
	public String exception() {
		String rsp = "";
		try {
			int i = 1 / 0;
			// should get exception
		} catch (Exception e) {
			e.printStackTrace();
            logger.error("StackTrace Error:", e);

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string
			logger.error("Exception As String :: - &gt; "+sStackTrace);

			rsp = sStackTrace;
		}

		return rsp;
	}
}
