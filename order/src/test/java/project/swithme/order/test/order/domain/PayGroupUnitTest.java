package project.swithme.order.test.order.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static project.swithme.order.core.domain.order.entity.PayGroup.findPayTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.order.core.domain.order.entity.PayGroup;

@DisplayName("[UnitTest] 결제 그룹 단위 테스트")
class PayGroupUnitTest {

    @ParameterizedTest
    @ValueSource(strings = {"CASH", "CARD"})
    @DisplayName("올바른 결제 그룹을 입력하면 결제 수단 리스트가 나온다.")
    void pay_type_list_search_test(String parameter) {
        PayGroup payGroup = PayGroup.valueOf(parameter);
        assertFalse(findPayTypes(payGroup).isEmpty());
    }
}
