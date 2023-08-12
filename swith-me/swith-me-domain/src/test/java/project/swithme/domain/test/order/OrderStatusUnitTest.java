package project.swithme.domain.test.order;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.domain.core.order.entity.OrderStatus;

@DisplayName("[UnitTest] 주문 상태 단위 테스트")
class OrderStatusUnitTest {

    @ParameterizedTest
    @ValueSource(strings = {"PAYMENT_REQUEST", "COMPLETE", "CANCEL", "REFUND"})
    @DisplayName("주문 상태를 조회할 수 있다.")
    void order_status_find_test(String parameter) {
        assertNotNull(OrderStatus.findStatus(parameter));
    }

    @ParameterizedTest
    @ValueSource(strings = {"HELLO", "WORLD"})
    @DisplayName("올바르지 않은 주문 상태를 입력하면 IllegalArgumentException이 발생한다.")
    void invalid_order_status_find_test(String parameter) {
        assertThatThrownBy(() -> OrderStatus.findStatus(parameter))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 주문 상태를 입력해주세요.");
    }
}
