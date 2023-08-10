package project.swithme.domain.test.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.domain.core.payment.entity.OwnerType;

@DisplayName("[UnitTest] 카드 유형 단위 테스트")
class OwnerTypeUnitTest {

    @Test
    @DisplayName("카드 유형으로 Null 값이 들어오면 UN_IDENTIFIED이 반환된다.")
    void owner_type_search_null_test() {
        assertEquals(OwnerType.UN_IDENTIFIED, OwnerType.findByType(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"개인", "법인", "미확인"})
    @DisplayName("올바른 카드 유형이 들어오면 객체를 조회할 수 있다.")
    void valid_owner_type_search_test(String parameter) {
        assertNotNull(OwnerType.findByType(parameter));
    }

    @ParameterizedTest
    @ValueSource(strings = {"개인2", "법2인", "2미확인"})
    @DisplayName("올바르지 않은 카드 유형이 들어오면 미확인 유형으로 조회된다.")
    void invalid_owner_type_search_test(String parameter) {
        assertEquals(OwnerType.UN_IDENTIFIED, OwnerType.findByType(parameter));
    }
}
