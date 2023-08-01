package project.swithme.order.test.payment.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static project.swithme.order.core.domain.payment.entity.SettlementStatus.findByStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[UnitTest] 정산 상태 단위 테스트")
class SettlementStatusUnitTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("정산 상태로 Null 값이 들어오면 Null이 반환된다.")
    void settlement_status_search_null_test(String parameter) {
        assertNull(findByStatus(parameter));
    }

    @ParameterizedTest
    @ValueSource(strings = {"INCOMPLETED", "COMPLETED"})
    @DisplayName("올바른 정산 상태가 들어오면 객체가 조회된다.")
    void valid_settlement_status_search_test(String parameter) {
        assertNotNull(findByStatus(parameter));
    }

    @ParameterizedTest
    @ValueSource(strings = {"IN_COM", "HELLO", "WORLD"})
    @DisplayName("올바르지 않은 정산 상태가 들어오면 IllegalArgumentException이 발생한다.")
    void invalid_settlement_status_search_test(String parameter) {
        assertThatThrownBy(() -> findByStatus(parameter))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 정산 상태를 입력해주세요.");
    }
}
