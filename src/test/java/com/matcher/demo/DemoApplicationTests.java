package com.matcher.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(classes = DemoApplication.class)
@DirtiesContext
@Testcontainers
class DemoApplicationTests {

    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));
    @Container
    static ElasticsearchContainer elasticsearch = new ElasticsearchContainer(DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch:7.16.2"));


    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("kafka.boostrap-servers", kafka::getBootstrapServers);
        registry.add("elasticsearch.host", elasticsearch::getHttpHostAddress);
    }

    @Value("${kafka.boostrap-servers}")
    private String kafkaserver;

    @Test
    void contextLoads() {
        System.out.println("kafka: " + kafkaserver);
    }

}
