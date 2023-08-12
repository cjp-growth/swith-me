package project.swithme.domain.test.order;

import static order.OrderFixture.createOrder;
import static order.OrderFixture.createOrderLine;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.domain.common.sut.order.OrderLineSut;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.order.entity.OrderLine;

@DisplayName("[UnitTest] 주문 상세 단위 테스트")
class OrderLineUnitTest {

    @Test
    @DisplayName("주문상세 객체를 생성할 수 있다.")
    void orderline_create_test() {
        OrderLine orderLine = OrderLine.createOrderLine(
            1L,
            1L,
            1L,
            BigDecimal.valueOf(10_000L)
        );

        assertNotNull(orderLine);

        OrderLineSut sut = new OrderLineSut(createOrderLine(1L));
        sut.shouldExist()
            .withStudyCafeId()
            .withProductId()
            .withBaseInformation();

        assertNotNull(sut);
    }

    @Test
    @DisplayName("주문을 FK로 등록할 수 있다.")
    void add_order_test() {
        OrderLine orderLine = createOrderLine(1L);
        Order order = createOrder(UUID.randomUUID());

        orderLine.add(order);

        assertNotNull(orderLine.getOrder());
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void equals_test() {
        OrderLine orderLine = createOrderLine(1L);
        OrderLine sameOrderLine = createOrderLine(1L);
        OrderLine otherOrderLine = createOrderLine(2L);

        assertTrue(orderLine.equals(sameOrderLine));
        assertTrue(!orderLine.equals(otherOrderLine));
        assertFalse(orderLine.equals("1"));
    }

    @Test
    @DisplayName("hashcode를 재정의 하면 값으로 객체를 비교한다.")
    void hashcode_test() {
        OrderLine orderLine = createOrderLine(1L);
        OrderLine otherOrderLine = createOrderLine(2L);

        assertTrue(orderLine.hashCode() == orderLine.hashCode());
        assertFalse(orderLine.hashCode() == otherOrderLine.hashCode());
    }

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        OrderLine orderLine = createOrderLine(1L);
        String expected = "orderLineId: " + orderLine.getId();

        assertEquals(expected, orderLine.toString());
    }
}
