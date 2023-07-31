package project.swithme.order.test.payment.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static project.swithme.order.common.testhelper.payment.fixture.TossFixture.createToss;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.order.core.domain.payment.entity.Toss;

@DisplayName("[UnitTest] 토스 결제 단위 테스트")
class TossUnitTest {

    @Test
    @DisplayName("Toss 객체를 생성할 수 있다.")
    void toss_create_test() {
        assertNotNull(createToss(1L));
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void equals_test() {
        Toss tossPayment = createToss(1L, 1L);
        Toss otherTossPayment = createToss(2L, 1L);

        assertTrue(tossPayment.equals(tossPayment));
        assertTrue(!tossPayment.equals(otherTossPayment));
        assertFalse("1".equals(tossPayment));
    }

    @Test
    @DisplayName("hashcode를 재정의 하면 값으로 객체를 비교한다.")
    void hashcode_test() {
        Toss tossPayment = createToss(1L);
        Toss sameTossPayment = createToss(1L);
        Toss otherTossPayment = createToss(2L, 1L);

        assertTrue(tossPayment.hashCode() == sameTossPayment.hashCode());
        assertFalse(tossPayment.hashCode() == otherTossPayment.hashCode());
    }

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        Toss toss = createToss(1L, 1L);

        assertEquals(toss.getId().toString(), toss.toString());
    }
}
