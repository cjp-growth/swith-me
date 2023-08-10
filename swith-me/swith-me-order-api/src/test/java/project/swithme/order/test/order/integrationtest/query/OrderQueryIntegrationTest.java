package project.swithme.order.test.order.integrationtest.query;

import static order.OrderFixture.createOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static project.swithme.domain.core.order.entity.OrderStatus.PAYMENT_REQUEST;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.order.core.application.OrderQueryUseCase;
import project.swithme.order.test.IntegrationTestBase;

@DisplayName("[IntegrationTest] 주문 조회 통합 테스트")
class OrderQueryIntegrationTest extends IntegrationTestBase {

    @Autowired
    private OrderQueryUseCase orderQueryUseCase;

    @Test
    @DisplayName("존재하지 않는 주문을 UUID로 조회하면 빈 객체가 조회된다.")
    void invalid_order_search_test() {
        Optional<Order> findOrder =
            orderQueryUseCase.findByUniqueId(UUID.randomUUID().toString());

        assertEquals(true, findOrder.isEmpty());
    }

    @Test
    @DisplayName("존재하는 주문을 UUID로 조회하면 주문을 찾을 수 있다.")
    void valid_order_search_test() {
        Order order = persistenceHelper.persist(createOrder(PAYMENT_REQUEST));
        Optional<Order> findOrder =
            orderQueryUseCase.findByUniqueId(order.getUniqueId().toString());

        assertEquals(true, findOrder.isPresent());
    }
}
