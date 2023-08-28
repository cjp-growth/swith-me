package project.swithme.notification.core.web.listener;

import static project.swithme.notification.common.message.PaymentMessageTemplate.PAYMENT_SUCCESS;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import project.swithme.domain.core.order.event.PaymentSuccessEvent;
import project.swithme.notification.core.web.facade.NotificationFacade;

@Component
@RequiredArgsConstructor
public class PaymentEventListener {

    private final NotificationFacade notificationFacade;

    @KafkaListener(topics = "orders")
    public void listenEvent(@Payload PaymentSuccessEvent event) {
        notificationFacade.sendNotification(
            event.getUserId(), event.getOrderUniqueId(), PAYMENT_SUCCESS.getMessage()
        );
    }
}
