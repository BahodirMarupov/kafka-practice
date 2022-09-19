package jms.workshop.kafkaproducer;

import jms.workshop.kafkaproducer.service.ProducerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;

@SpringBootApplication
@RequiredArgsConstructor
@RestController
@EnableTransactionManagement
public class KafkaProducerApplication {
    private final ProducerService producer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @GetMapping
    public String sendMessage() {
        producer.processWithTransaction("key1", "hello");
        return "Ok";
    }


}
