package project.swithme.payment.test.unittest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static project.swithme.domain.core.order.entity.PayGroup.findPayTypes;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.domain.core.order.entity.PayGroup;
import project.swithme.payment.common.configuration.business.PaymentGroup;

@DisplayName("[UnitTest] 결제/지불 그룹 단위 테스트")
class PaymentGroupUnitTest {

    private PaymentGroup paymentGroup;

    @BeforeEach
    void setUp() {
        List<PayGroup> payGroups = List.of(
            PayGroup.CASH,
            PayGroup.CARD
        );
        paymentGroup = new PaymentGroup(payGroups);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "NULL", "INVALID", "PARAMETER"})
    @DisplayName("공백 또는 올바르지 않은 결제 그룹을 입력하면 IllegalArgumentException이 발생한다.")
    void pay_type_search_failure_test(String parameter) {
        assertThatThrownBy(() -> paymentGroup.getPayTypes(parameter))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 결제 그룹을 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"CASH", "CARD"})
    @DisplayName("올바른 결제 그룹을 입력하면 결제 타입 목록이 조회된다.")
    void pay_type_search_success_test(String parameter) {
        assertEquals(
            findPayTypes(PayGroup.valueOf(parameter)),
            paymentGroup.getPayTypes(parameter)
        );
    }

    @Test
    @DisplayName("결제/지불 그룹을 생성하면 null이 아니다.")
    void payment_group_create_test() {
        List<PayGroup> payGroups = List.of(
            PayGroup.CASH,
            PayGroup.CARD
        );
        PaymentGroup paymentGroup = new PaymentGroup(payGroups);

        assertNotNull(paymentGroup);
    }
}
