package project.swithme.domain.test.order;

import static java.util.UUID.randomUUID;
import static order.OrderFixture.createOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static project.swithme.domain.core.order.entity.OrderStatus.PAYMENT_REQUEST;
import com.fasterxml.uuid.Generators;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.domain.common.sut.order.OrderSut;
import project.swithme.domain.core.common.BaseInformation;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.order.entity.OrderStatus;
import project.swithme.domain.core.order.entity.PayType;

@DisplayName("[UnitTest] 주문 단위 테스트")
class OrderDomainTest {

    private UUID orderUniqueId;

    @BeforeEach
    void setUp() {
        orderUniqueId = Generators.timeBasedGenerator().generate();
    }

    @Test
    void order_create_test() {
        Order order = createOrder(randomUUID());

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
    @DisplayName("주문 상태가 PAYMENT_REQUEST가 아니라면 FALSE를 반환한다.")
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
    @DisplayName("(카드사 할인으로 인해) 주문 가격을 변경하면 가격이 변경된다.")
    void order_price_change_test() {
        Order order = createOrder(orderUniqueId);

        order.updatePrice(new BigDecimal(100_000L));

        BigDecimal discountedPrice = order.getDiscountedTotalPrice();
        assertTrue(discountedPrice.compareTo(new BigDecimal(100_000L)) == 0);
    }

    @Test
    @DisplayName("(카드사 할인으로 인해) 주문 가격을 변경하더라도 할인 내역이 없다면 가격이 변경되지 않는다.")
    void order_price_change_null_test() {
        Order order = createOrder(orderUniqueId);

        order.updatePrice(null);

        BigDecimal discountedPrice = order.getDiscountedTotalPrice();
        assertTrue(discountedPrice.compareTo(new BigDecimal(130_000L)) == 0);
    }

    @Test
    @DisplayName("총 주문 가격을 조회할 수 있다.")
    void order_total_price_search_test() {
        Order order = createOrder(orderUniqueId);

        BigDecimal totalPrice = order.getTotalPrice();
        assertTrue(totalPrice.compareTo(new BigDecimal(130_000L)) == 0);
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void equals_test() {
        Order newOrder = new Order(
            1L,
            1L,
            1L,
            randomUUID(),
            "스터디 카페 1달 이용권",
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
            randomUUID(),
            "스터디 카페 1달 이용권",
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
    @DisplayName("hashcode를 재정의 하면 값으로 객체를 비교한다.")
    void hashcode_test() {
        Order newOrder = new Order(
            1L,
            1L,
            1L,
            randomUUID(),
            "스터디 카페 1달 이용권",
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
            randomUUID(),
            "스터디 카페 1달 이용권",
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
        String expected = "orderId: " + order.getId().toString();

        assertEquals(expected, order.toString());
    }
}
