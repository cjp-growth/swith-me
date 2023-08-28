package project.swithme.domain.core.order.event;

import lombok.Getter;

@Getter
public class PaymentSuccessEvent {

    private String orderUniqueId;
    private Long userId;

    private PaymentSuccessEvent() {
    }

    public PaymentSuccessEvent(
        String orderUniqueId,
        Long userId
    ) {
        this.orderUniqueId = orderUniqueId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return String.format("orderUniqueId: %s, userId: %s", orderUniqueId, userId);
    }
}
