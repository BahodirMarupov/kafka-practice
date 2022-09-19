package jms.workshop.kafkaproducer.service;

import jms.workshop.kafkaproducer.Producer;
import jms.workshop.kafkaproducer.config.KafkaDemoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProducerService {
    @Autowired
    private KafkaDemoProperties properties;

    @Autowired
    private Producer kafkaClient;


    @Transactional
    public void processWithTransaction(String key, String event) {
        kafkaClient.sendMessageWithTransaction(key, event, properties.getOutboundTopic1());
        kafkaClient.sendMessageWithTransaction(key, event, properties.getOutboundTopic2());
    }


}
