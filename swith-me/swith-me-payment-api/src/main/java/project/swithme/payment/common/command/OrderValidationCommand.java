package project.swithme.payment.common.command;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import project.swithme.domain.core.order.entity.OrderStatus;

@Getter
public class OrderValidationCommand {

    private Long orderId;
    private Long userId;
    private UUID orderUniqueId;
    private OrderStatus orderStatus;
    private BigDecimal price;

    public OrderValidationCommand(
        Long orderId,
        Long userId,
        UUID orderUniqueId,
        OrderStatus orderStatus,
        BigDecimal price
    ) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderUniqueId = orderUniqueId;
        this.orderStatus = orderStatus;
        this.price = price;
    }

    public boolean isValidStatus() {
        return this.orderStatus.equals(OrderStatus.PAYMENT_REQUEST);
    }

    public boolean validatePrice(BigDecimal amount) {
        return this.price.equals(amount);
    }
}
