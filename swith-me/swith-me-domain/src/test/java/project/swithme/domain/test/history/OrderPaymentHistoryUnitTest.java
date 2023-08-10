package project.swithme.domain.test.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Instant;
import java.time.temporal.ChronoField;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.domain.core.history.entity.OrderPaymentHistory;

@DisplayName("[UnitTest] 주문/결제 내역 단위 테스트")
class OrderPaymentHistoryUnitTest {

    @Test
    @DisplayName("PK와 현재 시간을 알면 주문/결제 객체를 생성할 수 있다.")
    void history_create_test() {
        Instant now = Instant.now();
        OrderPaymentHistory history = new OrderPaymentHistory(
            1L,
            now.getLong(ChronoField.NANO_OF_SECOND)
        );

        assertNotNull(history);
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void equals_test() {
        Instant now = Instant.now();
        OrderPaymentHistory history = new OrderPaymentHistory(
            1L,
            now.getLong(ChronoField.NANO_OF_SECOND)
        );

        OrderPaymentHistory otherHistory = new OrderPaymentHistory(
            2L,
            now.getLong(ChronoField.NANO_OF_SECOND)
        );

        assertTrue(history.equals(history));
        assertFalse(history.equals(otherHistory));
        assertFalse("1".equals(history));
    }


    @Test
    @DisplayName("hashcode를 재정의 하면 값으로 객체를 비교한다.")
    void hashcode_test() {
        Instant now = Instant.now();
        OrderPaymentHistory history = new OrderPaymentHistory(
            1L,
            now.getLong(ChronoField.NANO_OF_SECOND)
        );

        OrderPaymentHistory otherHistory = new OrderPaymentHistory(
            2L,
            now.getLong(ChronoField.NANO_OF_SECOND)
        );

        assertTrue(history.hashCode() == history.hashCode());
        assertFalse(history.hashCode() == otherHistory.hashCode());
    }

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        Instant now = Instant.now();
        OrderPaymentHistory history = new OrderPaymentHistory(
            1L,
            now.getLong(ChronoField.NANO_OF_SECOND)
        );
        String expected = "revId: " + history.getId();

        assertEquals(expected, history.toString());
    }
}
