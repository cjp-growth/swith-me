package project.swithme.domain.common.sut.order;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import project.swithme.domain.core.order.entity.OrderLine;

public final class OrderLineSut {

    private final OrderLine orderLine;

    public OrderLineSut(OrderLine orderLine) {
        this.orderLine = orderLine;
    }

    private OrderLineSut() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public OrderLineSut shouldExist() {
        assertNotNull(orderLine.getId());
        return this;
    }

    public OrderLineSut withStudyCafeId() {
        assertNotNull(orderLine.getStudyCafeId());
        return this;
    }

    public OrderLineSut withProductId() {
        assertNotNull(orderLine.getProductId());
        return this;
    }

    public OrderLineSut withBaseInformation() {
        assertNotNull(orderLine.getBaseInformation());
        return this;
    }
}
