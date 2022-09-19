package jms.workshop.kafkaproducer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

@Configuration
@ConfigurationProperties("kafkademo")
@Getter
@Setter
@Validated
public class KafkaDemoProperties {
    @NotNull
    private String id;
    @NotNull
    private String outboundTopic1;
    @NotNull
    private String outboundTopic2;

    @NotNull
    private UUID instanceId = UUID.randomUUID();
}