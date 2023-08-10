package project.swithme.domain.test.payment;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.domain.core.payment.entity.Bank;

@DisplayName("[UnitTest] 은행 코드 단위 테스트")
class BankCodeUnitTest {

    @ParameterizedTest
    @ValueSource(strings = {"99999", "00000", "9102431204", "1234141"})
    @DisplayName("올바르지 않은 은행 코드를 입력하면 IllegalArgumentException이 발생한다.")
    void invalid_bank_code_search_test(String parameter) {
        assertThatThrownBy(() -> Bank.findByCode(parameter))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 은행 코드를 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"039", "39"})
    @DisplayName("올바른 은행 코드를 입력하면 은행 객체가 검색된다.")
    void valid_bank_code_search_test(String parameter) {
        assertNotNull(Bank.findByCode(parameter));
    }
}
