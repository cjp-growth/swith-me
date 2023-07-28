package project.swithme.order.test.payment.integrationtest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static project.swithme.order.common.testhelper.order.fixture.OrderFixture.createOrder;
import static project.swithme.order.common.testhelper.order.fixture.OrderFixture.createOrderUniqueId;
import static project.swithme.order.common.testhelper.payment.fixture.PaymentFixture.createTossPayment;
import static project.swithme.order.core.domain.order.entity.OrderStatus.COMPLETE;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.order.infrastructure.OrderJpaRepository;
import project.swithme.order.core.domain.payment.entity.Payment;
import project.swithme.order.core.web.order.exception.InvalidOrderStatusException;
import project.swithme.order.core.web.order.exception.OrderNotFoundException;
import project.swithme.order.core.web.payment.exception.InvalidPriceException;
import project.swithme.order.core.web.payment.facade.PaymentFacade;
import project.swithme.order.test.IntegrationTestBase;

@DisplayName("[IntegrationTest] 결제 통합 테스트")
class PaymentIntegrationTest extends IntegrationTestBase {

    @Autowired
    private PaymentFacade paymentFacade;

    @Autowired
    private OrderJpaRepository orderRepository;

    @Test
    @DisplayName("토스 결제 Callback이 제대로 오면 결제가 성공하고 PK가 Null이 아니다.")
    void toss_pay_callback_success_test() {
        UUID uuid = createOrderUniqueId();

        Order order = createOrder(null, uuid);
        Order newOrder = orderRepository.save(order);

        Payment payment = createTossPayment(uuid.toString(), newOrder.getTotalPrice());
        Long paymentId = paymentFacade.pay(newOrder.getUniqueId().toString(), payment);

        assertNotNull(paymentId);
    }

    @Test
    @DisplayName("토스 결제 Callback에서 주문 가격과 비교해 최종 금액이 일치하지 않으면 InvalidPriceException이 발생한다.")
    void toss_pay_callback_invalid_price_exception_test() {
        UUID uuid = createOrderUniqueId();
        Order order = createOrder(null, uuid);
        Order newOrder = orderRepository.save(order);

        Payment payment = createTossPayment(
            uuid.toString(),
            new BigDecimal(100_000_000L)
        );

        assertThatThrownBy(
            () -> paymentFacade.pay(newOrder.getUniqueId().toString(), payment)
        ).isExactlyInstanceOf(InvalidPriceException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 가격을 입력해주세요", "PAYMENT");
    }

    @Test
    @DisplayName("토스 결제 Callback에서 주문 UniqueId가 다르면 OrderNotFoundException이 발생한다.")
    void toss_pay_callback_order_not_found_exception_test() {
        UUID uuid = createOrderUniqueId();
        Order newOrder = orderRepository.save(createOrder(null, uuid));

        UUID otherUuid = createOrderUniqueId();
        Payment payment = createTossPayment(
            otherUuid.toString(),
            newOrder.getTotalPrice()
        );

        assertThatThrownBy(
            () -> paymentFacade.pay(otherUuid.toString(), payment)
        ).isExactlyInstanceOf(OrderNotFoundException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("해당 주문을 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("토스 결제 Callback에서 주문 UniqueId가 다르면 InvalidOrderStatusException이 발생한다.")
    void toss_pay_callback_invalid_order_status_test() {
        UUID uuid = createOrderUniqueId();
        Order order = createOrder(null, uuid, COMPLETE);
        Order newOrder = orderRepository.save(order);

        Payment payment = createTossPayment(
            uuid.toString(),
            newOrder.getTotalPrice()
        );

        assertThatThrownBy(
            () -> paymentFacade.pay(uuid.toString(), payment)
        ).isExactlyInstanceOf(InvalidOrderStatusException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바르지 않은 주문 상태입니다.");
    }
}
