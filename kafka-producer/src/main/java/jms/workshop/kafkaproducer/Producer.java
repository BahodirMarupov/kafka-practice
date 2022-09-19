package jms.workshop.kafkaproducer;

import jms.workshop.kafkaproducer.config.KafkaDemoProperties;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
public class Producer {
    @Autowired
    private KafkaDemoProperties properties;

    @Autowired
    private KafkaTemplate kafkaTemplateTransactional;


    public void sendMessageWithTransaction(String key, String data, String outboundTopic) {
        sendMessage("Transactional", kafkaTemplateTransactional, key, data, outboundTopic);
    }


    public void sendMessage(String type, KafkaTemplate kafkaTemplate, String key, String data, String outboundTopic) {
        try {
            String payload = "eventId: " + UUID.randomUUID() + ", instanceId: " + properties.getInstanceId() + ", payload: " + data;
            final ProducerRecord<String, String> record =
                    new ProducerRecord<>(outboundTopic, key, payload);

            final SendResult result = (SendResult) kafkaTemplate.send(record).get();
            final RecordMetadata metadata = result.getRecordMetadata();

            log.debug(String.format("Sent %s record(key=%s value=%s) meta(topic=%s, partition=%d, offset=%d)",
                    type, record.key(), record.value(), metadata.topic(), metadata.partition(), metadata.offset()));

        } catch (Exception e) {
            log.error("Error sending message to topic: " + outboundTopic, e);
            throw new RuntimeException(e);
        }
    }
}
