package project.swithme.order.test.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.order.core.domain.order.entity.PayType;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DisplayName("[UnitTest] 결제 수단 단위 테스트")
class PayTypeUnitTest {

    @ParameterizedTest
    @ValueSource(strings = {"신용카드", "카카오 페이", "네이버 페이", "토스 페이", "무통장 입금"})
    @DisplayName("올바른 결제 수단을 입력하면 PayType 객체를 찾을 수 있다.")
    void pay_type_find_success_test(String parameter) {
        assertNotNull(PayType.findPayType(parameter));
    }

    @ParameterizedTest
    @ValueSource(strings = {"PayPal", "삼성 페이", "알라딘 페이"})
    @DisplayName("올바르지 않은 결제 수단을 입력하면 Ill")
    void pay_type_find_failure_test(String parameter) {
        assertThatThrownBy(() -> PayType.findPayType(parameter))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("올바른 결제 수단을 입력해주세요.");
    }
}
