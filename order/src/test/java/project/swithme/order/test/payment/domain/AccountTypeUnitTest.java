package project.swithme.order.test.payment.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static project.swithme.order.core.domain.payment.entity.AccountType.findByType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[UnitTest] 계좌 유형 단위 테스트")
class AccountTypeUnitTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("계좌 유형으로 Null 값이 들어오면 Null이 반환된다.")
    void account_type_search_null_test(String parameter) {
        assertNull(findByType(parameter));
    }

    @ParameterizedTest
    @ValueSource(strings = {"일반", "고정"})
    @DisplayName("올바른 계좌 유형이 들어오면 객체가 조회된다.")
    void valid_account_type_search_test(String parameter) {
        assertNotNull(findByType(parameter));
    }

    @ParameterizedTest
    @ValueSource(strings = {"IN_COM", "HELLO", "WORLD"})
    @DisplayName("올바르지 않은 계좌 유형이 들어오면 IllegalArgumentException이 발생한다.")
    void invalid_account_type_search_test(String parameter) {
        assertThatThrownBy(() -> findByType(parameter))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 계좌 유형을 입력해주세요.");
    }
}
