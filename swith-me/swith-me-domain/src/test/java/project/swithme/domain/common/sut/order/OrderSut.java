package project.swithme.domain.common.sut.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.List;
import project.swithme.domain.core.common.BaseInformation;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.order.entity.OrderLine;

public final class OrderSut {

    private final Order order;

    public OrderSut(Order order) {
        this.order = order;
    }

    private OrderSut() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public OrderSut shouldExist() {
        assertNotNull(order);
        assertNotNull(order.getId());
        return this;
    }

    public OrderSut withUserId() {
        assertNotNull(order.getUserId());
        return this;
    }

    public OrderSut withReservationId() {
        assertNull(order.getReservationId());
        return this;
    }

    public OrderSut withUniqueId() {
        assertNotNull(order.getUniqueId());
        return this;
    }

    public OrderSut withTitle() {
        assertNotNull(order.getTitle());
        return this;
    }

    public OrderSut withPayType() {
        assertNotNull(order.getPayType());
        return this;
    }

    public OrderSut withOrderStatus() {
        assertNotNull(order.getOrderStatus());
        return this;
    }

    public OrderSut withDepositDeadline() {
        assertNotNull(order.getDepositDeadline());
        return this;
    }

    public OrderSut withoutRefundReason() {
        assertNull(order.getRefundReason());
        return this;
    }

    public OrderSut withOrderLines() {
        List<OrderLine> orderLines = order.getOrderLines();
        assertFalse(orderLines.isEmpty());
        return this;
    }

    public OrderSut withBasicInformation() {
        BaseInformation baseInformation = order.getBaseInformation();
        assertNotNull(baseInformation.getCreatedBy());
        assertNull(baseInformation.getLastModifiedBy());
        return this;
    }
}
