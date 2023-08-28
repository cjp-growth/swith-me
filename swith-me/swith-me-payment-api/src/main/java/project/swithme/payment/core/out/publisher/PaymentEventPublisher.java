package project.swithme.payment.core.out.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import project.study.support.exception.MessageSendFailureException;
import project.swithme.domain.core.order.event.PaymentSuccessEvent;
import project.swithme.payment.core.out.EventPublisher;

@Component
@RequiredArgsConstructor
public class PaymentEventPublisher implements EventPublisher<PaymentSuccessEvent> {

    private final KafkaTemplate<String, PaymentSuccessEvent> kafkaTemplate;

    @Override
    public void publishEvent(PaymentSuccessEvent paymentSuccessEvent) {
        try {
            kafkaTemplate.send("orders", "orders", paymentSuccessEvent);
        } catch (RuntimeException exception) {
            throw new MessageSendFailureException();
        }
    }
}
