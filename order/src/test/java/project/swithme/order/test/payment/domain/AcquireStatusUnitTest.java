package project.swithme.order.test.payment.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static project.swithme.order.core.domain.payment.entity.AcquireStatus.findByStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[UnitTest] 카드 결제 매입 상태 단위 테스트")
class AcquireStatusUnitTest {

    @ParameterizedTest
    @ValueSource(strings = {"AZZZA_UN_RDY", "AZZA_UN_CANCEL", "AAZA_NO_COM", "AFEA_12141"})
    @DisplayName("올바르지 않은 카드 결제 매입 상태를 입력하면 IllegalArgumentException이 발생한다.")
    void invalid_acquire_status_search_test(String parameter) {
        assertThatThrownBy(() -> findByStatus(parameter))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 카드 결제 매입 상태를 입력해주세요.");
    }
}
