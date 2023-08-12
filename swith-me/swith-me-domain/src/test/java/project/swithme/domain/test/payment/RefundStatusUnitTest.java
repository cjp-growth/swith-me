package project.swithme.domain.test.payment;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.domain.core.payment.entity.RefundStatus;

@DisplayName("[UnitTest] 환불 상태 단위 테스트")
class RefundStatusUnitTest {

    @ParameterizedTest
    @ValueSource(strings = {"NONE", "PENDING", "FAILED", "PARTIAL_FAILED", "COMPLETED"})
    @DisplayName("환불 상태를 조회할 수 있다.")
    void refund_status_search_test(String parameter) {
        assertNotNull(RefundStatus.findByStatus(parameter));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("환불 상태가 Null이면 Null 값이 조회된다.")
    void null_refund_status_search_test(String parameter) {
        assertNull(RefundStatus.findByStatus(parameter));
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "B", "C", "D", "E", "F"})
    @DisplayName("올바르지 않은 환불 상태를 입력하면 IllegalArgumentException이 발생한다.")
    void invalid_refund_status_search_test(String parameter) {
        assertThatThrownBy(() -> RefundStatus.findByStatus(parameter))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 환불처리 상태를 입력해주세요.");
    }
}
