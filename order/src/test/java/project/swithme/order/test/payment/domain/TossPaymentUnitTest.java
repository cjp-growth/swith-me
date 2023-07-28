package project.swithme.order.test.payment.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static project.swithme.order.common.testhelper.payment.fixture.PaymentFixture.createOrderUniqueId;
import static project.swithme.order.common.testhelper.payment.fixture.PaymentFixture.createTossPayment;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import project.swithme.order.core.domain.payment.entity.Payment;
import project.swithme.order.core.domain.payment.entity.toss.PaymentType;
import project.swithme.order.core.domain.payment.entity.toss.pojo.payments.TossPayment;
import project.swithme.order.core.web.payment.exception.InvalidOrderIdException;

@DisplayName("[UnitTest] 토스 결제 단위 테스트")
class TossPaymentUnitTest {

    @Test
    @DisplayName("토스 결제 객체를 생성하면 PK가 Null이다.")
    void toss_payment_create_test() {
        Payment tossPayment = createTossPayment(createOrderUniqueId());

        assertNotNull(tossPayment);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("토스 결제 객체를 생성하면 PK가 Null이다.")
    void toss_payment_create_failure_test(String parameter) {
        assertThatThrownBy(() -> createTossPayment(parameter))
            .isExactlyInstanceOf(InvalidOrderIdException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 주문 아이디를 입력해주세요.");
    }

    @Test
    @DisplayName("equals와 hashcode를 재정의 하면 값으로 객체를 비교한다.")
    void equals_hashcode_test() {
        Payment tossPayment = createTossPayment(createOrderUniqueId());

        Payment otherPayment = new TossPayment(
            createOrderUniqueId(),
            "toss-payment-key",
            PaymentType.NORMAL,
            new BigDecimal(100_000L)
        );

        assertTrue(tossPayment.equals(tossPayment));
        assertFalse(otherPayment.equals(tossPayment));
    }

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        String uuid = createOrderUniqueId();
        Payment tossPayment = createTossPayment(uuid);

        String toStringValue = String.format(
            "OrderUniqueId: %s, PaymentType: %s, PaymentKey: %s, Amount: %s",
            uuid,
            tossPayment.getPaymentType(),
            tossPayment.getPaymentKey(),
            tossPayment.getPrice()
        );

        assertEquals(toStringValue, tossPayment.toString());
    }
}
