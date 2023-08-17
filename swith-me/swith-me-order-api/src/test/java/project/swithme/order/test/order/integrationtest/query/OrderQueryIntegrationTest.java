package project.swithme.order.test.order.integrationtest.query;

import static order.OrderFixture.createOrder;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static project.swithme.domain.core.order.entity.OrderStatus.PAYMENT_REQUEST;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.study.support.exception.UnAuthorizedException;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.order.core.application.OrderQueryUseCase;
import project.swithme.order.core.exception.OrderNotFoundException;
import project.swithme.order.test.IntegrationTestBase;

@DisplayName("[IntegrationTest] 주문 조회 통합 테스트")
class OrderQueryIntegrationTest extends IntegrationTestBase {

    @Autowired
    private OrderQueryUseCase orderQueryUseCase;

    @Test
    @DisplayName("주문이 존재하면 PK로 조회할 수 있다.")
    void valid_order_search_by_id_test() {
        Order newOrder = persistenceHelper.persist(createOrder(PAYMENT_REQUEST));
        Order findOrder = orderQueryUseCase.findOrderById(
            new StudyWithMeUser(newOrder.getUserId()),
            newOrder.getId()
        );

        assertNotNull(findOrder);
    }

    @Test
    @DisplayName("존재하지 않는 주문을 조회하면 OrderNotFoundException이 발생한다.")
    void invalid_order_search_by_id_test() {
        Order newOrder = persistenceHelper.persist(createOrder(PAYMENT_REQUEST));
        Long invalidOrderId = Long.MAX_VALUE;

        assertThatThrownBy(() -> orderQueryUseCase.findOrderById(
            new StudyWithMeUser(newOrder.getUserId()),
            invalidOrderId
        ))
            .isInstanceOf(OrderNotFoundException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("주문을 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("자신의 주문이 아니라면 UnAuthorizedException이 발생한다.")
    void order_search_by_id_un_authorized_test() {
        Order newOrder = persistenceHelper.persist(createOrder(PAYMENT_REQUEST));
        Long otherUserId = Long.MAX_VALUE;

        assertThatThrownBy(() -> orderQueryUseCase.findOrderById(
            new StudyWithMeUser(otherUserId),
            newOrder.getId()
        ))
            .isInstanceOf(UnAuthorizedException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("권한이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("존재하지 않는 주문을 UUID로 조회하면 빈 객체가 조회된다.")
    void invalid_order_search_by_unique_id_test() {
        Optional<Order> findOrder =
            orderQueryUseCase.findByUniqueId(UUID.randomUUID().toString());

        assertEquals(true, findOrder.isEmpty());
    }

    @Test
    @DisplayName("존재하는 주문을 UUID로 조회하면 주문을 찾을 수 있다.")
    void valid_order_search_by_unique_id_test() {
        Order order = persistenceHelper.persist(createOrder(PAYMENT_REQUEST));
        Optional<Order> findOrder =
            orderQueryUseCase.findByUniqueId(order.getUniqueId().toString());

        assertEquals(true, findOrder.isPresent());
    }
}
