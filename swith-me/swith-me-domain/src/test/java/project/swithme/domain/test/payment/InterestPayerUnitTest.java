package project.swithme.domain.test.payment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.domain.core.payment.entity.InterestPayer;

@DisplayName("[UnitTest] 할부 수수료 부담 주체 단우 ㅣ테스트")
class InterestPayerUnitTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("할부 수수료 부담 주체 값으로 Null이 들어오면 Null이 반환된다.")
    void interest_payer_search_null_test(String parameter) {
        assertNull(InterestPayer.findByPayer(parameter));
    }

    @ParameterizedTest
    @ValueSource(strings = {"BUYER", "CARD_COMPANY", "MERCHANT"})
    @DisplayName("올바른 할부 수수료 부담 주체 값이 들어오면 할부 수수료 부담 주체가 조회된다.")
    void valid_interest_payer_search_test(String parameter) {
        assertNotNull(InterestPayer.findByPayer(parameter));
    }

    @ParameterizedTest
    @ValueSource(strings = {"AMERICA", "JAPAN", "INVALID"})
    @DisplayName("올바르지 않은 할부 수수료 부담 주체 값이 들어오면 IllegalArgumentException이 발생한다.")
    void invalid_interest_payer_search_test(String parameter) {
        assertThatThrownBy(() -> InterestPayer.findByPayer(parameter))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 할부 수수료 부담 주체를 입력해주세요.");
    }
}
