package project.swithme.order.test.payment.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static project.swithme.order.core.domain.payment.entity.CardIssuerCompany.findByCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[UnitTest] 카드 발행사 단위 테스트")
class CardIssuerCompanyUnitTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "61", "51", "71", "24", "21", "11", "31",
        "91", "15", "3A", "3K", "36", "38", "37",
        "34", "62", "46", "35", "30", "42", "39",
        "33", "4V", "4M", "3C"
    })
    @DisplayName("올바른 카드 발행사 코드가 있으면 카드 발행사를 알 수 있다.")
    void valid_card_issuer_company_search_test(String parameter) {
        assertNotNull(findByCode(parameter));
    }

    @ParameterizedTest
    @ValueSource(strings = {"AAA", "BBB", "CCC", "DDD", "DDD", "FFF"})
    @DisplayName("올바르지 않은 카드 발행사 코드가 입력되면 IllegalArgumentException이 발생한다.")
    void invalid_card_issuer_company_search_test(String parameter) {
        assertThatThrownBy(() -> findByCode(parameter))
            .isExactlyInstanceOf(IllegalArgumentException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 카드 발행사 코드를 입력해주세요.");
    }
}
