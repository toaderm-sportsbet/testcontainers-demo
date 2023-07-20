package com.matcher.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@Value("kafka.boostrap-server")
	static String kafkaServer;
	@Value("elasticsearch.host")
	static String elasticSearch;

	public static void main(String[] args) {
		System.out.println(kafkaServer);
		System.out.println(elasticSearch);
		SpringApplication.run(DemoApplication.class, args);
	}

}
