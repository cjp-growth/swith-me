package project.swithme.order.core.domain.payment.entity.toss.pojo.payments;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import project.swithme.order.core.domain.order.entity.PayType;
import project.swithme.order.core.domain.payment.entity.Payment;
import project.swithme.order.core.domain.payment.entity.toss.PaymentType;
import project.swithme.order.core.web.payment.exception.InvalidOrderIdException;

@Getter
public class TossPayment implements Payment {

    private final UUID orderUniqueId;
    private final PaymentType paymentType;
    private final PayType payType = PayType.TOSS;
    private final String paymentKey;
    private final BigDecimal amount;
    private Long userId;
    private Long orderId;

    public TossPayment(
        String orderUniqueId,
        String paymentKey,
        PaymentType paymentType,
        BigDecimal amount
    ) {
        this.orderUniqueId = createOrderUniqueId(orderUniqueId);
        this.paymentKey = paymentKey;
        this.paymentType = paymentType;
        this.amount = amount;
    }

    private UUID createOrderUniqueId(String orderUniqueId) {
        if (orderUniqueId == null || orderUniqueId.isBlank()) {
            throw new InvalidOrderIdException();
        }
        try {
            return UUID.fromString(orderUniqueId);
        } catch (IllegalArgumentException e) {
            throw new InvalidOrderIdException();
        }
    }

    @Override
    public String getOrderUniqueId() {
        return orderUniqueId.toString();
    }

    @Override
    public BigDecimal getPrice() {
        return amount;
    }

    @Override
    public void register(
        Long orderId,
        Long userId
    ) {
        this.orderId = orderId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof TossPayment that)) {
            return false;
        }
        return getOrderUniqueId().equals(that.getOrderUniqueId())
            && getPaymentType() == that.getPaymentType() && getPaymentKey().equals(
            that.getPaymentKey())
            && getAmount().equals(that.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderUniqueId(), getPaymentType(), getPaymentKey(), getAmount());
    }

    @Override
    public String toString() {
        return String.format(
            "OrderUniqueId: %s, PaymentType: %s, PaymentKey: %s, Amount: %s",
            orderUniqueId, paymentType, paymentKey, amount
        );
    }
}
