package project.swithme.order.test.payment.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.order.core.domain.payment.entity.PaymentMethod;

@DisplayName("[UnitTest] 결제 방법 단위 테스트")
class PaymentMethodUnitTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "카드", "가상계좌", "간편결제", "휴대폰", "계좌이체", "문화상품권", "도서문화상품권", "게임문화상품권"
    })
    @DisplayName("올바른 결제 방법을 입력하면 결제 방법이 조회된다.")
    void valid_method_search_test(String parameter) {
        assertNotNull(PaymentMethod.findMethod(parameter));
    }

    @ParameterizedTest
    @ValueSource(strings = {"공짜", "무이자"})
    @DisplayName("올바르지 않은 결제 방법을 조회하면 IllegalArgument이 발생한다.")
    void invalid_method_search_test(String parameter) {
        assertThatThrownBy(() -> PaymentMethod.findMethod(parameter))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 결제 수단을 입력해주세요.");
    }
}
