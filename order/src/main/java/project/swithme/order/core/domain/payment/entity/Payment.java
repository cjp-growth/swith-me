package project.swithme.order.core.domain.payment.entity;

import java.math.BigDecimal;
import project.swithme.order.core.domain.payment.entity.toss.PaymentType;

public interface Payment {

    void register(Long orderId, Long userId);

    Long getUserId();

    Long getOrderId();

    String getOrderUniqueId();

    String getPaymentKey();

    PaymentType getPaymentType();

    BigDecimal getPrice();
}
