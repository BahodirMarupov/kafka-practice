package jms.workshop.kafkaconsumer.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.security.oauthbearer.secured.Retryable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class Consumer {
    @KafkaListener(topics = "demo-transactional-inbound-topic", groupId = "kafkaConsumerGroup", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload final String payload) {
        try {
            log.info(payload);
        } catch (Exception e) {
            if (e instanceof Retryable) {
                log.debug("Throwing retryable exception.");
                throw e;
            }
            log.error("Error processing message: " + e.getMessage());
        }
    }


}
