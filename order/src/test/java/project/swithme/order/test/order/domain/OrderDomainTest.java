package project.swithme.order.test.order.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static project.swithme.order.common.testhelper.order.fixture.OrderFixture.createOrder;
import static project.swithme.order.core.domain.order.entity.OrderStatus.PAYMENT_REQUEST;
import com.fasterxml.uuid.Generators;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.order.common.testhelper.order.sut.OrderSut;
import project.swithme.order.core.common.BaseInformation;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.order.entity.OrderLine;
import project.swithme.order.core.domain.order.entity.OrderStatus;
import project.swithme.order.core.domain.order.entity.PayType;

@DisplayName("[UnitTest] 주문 단위 테스트")
class OrderDomainTest {

    private UUID orderUniqueId;

    @BeforeEach
    void setUp() {
        orderUniqueId = Generators.timeBasedGenerator().generate();
    }

    @Test
    void order_create_test() {
        List<OrderLine> orderLines = new ArrayList<>();
        OrderLine studyCafe = OrderLine.createOrderLine(1L, 1L, 1L, BigDecimal.valueOf(100_000L));
        OrderLine locker = OrderLine.createOrderLine(1L, 1L, 1L, BigDecimal.valueOf(30_000L));
        orderLines.add(studyCafe);
        orderLines.add(locker);

        Order order = new Order(
            1L,
            1L,
            1L,
            UUID.randomUUID(),
            PayType.TOSS,
            PAYMENT_REQUEST,
            Instant.now().plus(Duration.ofHours(1)),
            "",
            orderLines,
            new BaseInformation(1L)
        );

        OrderSut sut = new OrderSut(order)
            .shouldExist()
            .withUserId()
            .withReservationId()
            .withUniqueId()
            .withPayType()
            .withOrderStatus()
            .withDepositDeadline()
            .withoutRefundReason()
            .withOrderLines()
            .withBasicInformation();

        assertNotNull(sut);
    }

    @ParameterizedTest
    @ValueSource(strings = {"COMPLETE", "CANCEL", "REFUND"})
    @DisplayName("주문 상태가 PAYMENT_REQUEST가 아니라면 TRUE를 반환한다.")
    void order_invalid_status_test(String parameter) {
        Order order = createOrder(OrderStatus.valueOf(parameter));

        assertFalse(order.isValidStatus());
    }

    @Test
    @DisplayName("주문 상태가 PAYMENT_REQUEST라면 TRUE를 반환한다.")
    void order_valid_status_test() {
        Order order = createOrder(PAYMENT_REQUEST);

        assertTrue(order.isValidStatus());
    }

    @Test
    @DisplayName("총 상품 가격이 다르면 FALSE를 반환한다.")
    void invalid_price_versus_test() {
        Order order = createOrder(orderUniqueId);

        BigDecimal inValidPrice = new BigDecimal(300_000_000L);
        boolean result = order.validatePrice(inValidPrice);

        assertFalse(result);
    }

    @Test
    @DisplayName("총 상품 가격이 동일하면 TRUE를 반환한다.")
    void valid_price_versus_test() {
        Order order = createOrder(orderUniqueId);

        assertTrue(order.validatePrice(new BigDecimal("130000")));
    }

    @Test
    @DisplayName("주문 상태를 변경하면 상태가 변경된다.")
    void order_status_change_test() {
        Order order = createOrder(orderUniqueId);

        order.updateOrderStatus(OrderStatus.COMPLETE);

        assertEquals(OrderStatus.COMPLETE, order.getOrderStatus());
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void equals_test() {
        Order newOrder = new Order(
            1L,
            1L,
            1L,
            UUID.randomUUID(),
            PayType.TOSS,
            PAYMENT_REQUEST,
            Instant.now().plus(Duration.ofHours(1)),
            "",
            new ArrayList<>(),
            new BaseInformation(1L)
        );

        Order otherOrder = new Order(
            2L,
            1L,
            1L,
            UUID.randomUUID(),
            PayType.TOSS,
            PAYMENT_REQUEST,
            Instant.now().plus(Duration.ofHours(1)),
            "",
            new ArrayList<>(),
            new BaseInformation(1L)
        );

        assertTrue(newOrder.equals(newOrder));
        assertTrue(!newOrder.equals(otherOrder));
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void hashcode_test() {
        Order newOrder = new Order(
            1L,
            1L,
            1L,
            UUID.randomUUID(),
            PayType.TOSS,
            PAYMENT_REQUEST,
            Instant.now().plus(Duration.ofHours(1)),
            "",
            new ArrayList<>(),
            new BaseInformation(1L)
        );

        Order otherOrder = new Order(
            2L,
            1L,
            1L,
            UUID.randomUUID(),
            PayType.TOSS,
            PAYMENT_REQUEST,
            Instant.now().plus(Duration.ofHours(1)),
            "",
            new ArrayList<>(),
            new BaseInformation(1L)
        );

        assertTrue(newOrder.hashCode() == newOrder.hashCode());
        assertFalse(newOrder.hashCode() == otherOrder.hashCode());
    }

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        Order order = createOrder(orderUniqueId);

        assertEquals(order.getId().toString(), order.toString());
    }
}