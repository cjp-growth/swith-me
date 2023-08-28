package project.swithme.payment.common.configuration.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import project.swithme.domain.core.order.event.PaymentSuccessEvent;

@Configuration
public class KafkaPublisherConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String serverIpAddress;

    @Bean
    public ProducerFactory<String, PaymentSuccessEvent> messageProducerFactory() {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serverIpAddress);
        configuration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configuration);
    }

    @Bean
    public KafkaTemplate<String, PaymentSuccessEvent> kafkaTemplate() {
        return new KafkaTemplate<>(messageProducerFactory());
    }
}
