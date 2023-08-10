package project.swithme.domain.test.payment;

import static order.PaymentFixture.createPayment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.domain.core.payment.entity.Payment;

@DisplayName("[UnitTest] 결제 단위 테스트")
class PaymentUnitTest {

    @Test
    @DisplayName("결제 객체를 생성할 수 있다.")
    void payment_create_test() {
        assertNotNull(createPayment(1L));
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void equals_test() {
        Payment paymentPayment = createPayment(1L, 1L);
        Payment otherPaymentPayment = createPayment(2L, 1L);

        assertTrue(paymentPayment.equals(paymentPayment));
        assertTrue(!paymentPayment.equals(otherPaymentPayment));
        assertFalse("1".equals(paymentPayment));
    }

    @Test
    @DisplayName("hashcode를 재정의 하면 값으로 객체를 비교한다.")
    void hashcode_test() {
        Payment paymentPayment = createPayment(1L);
        Payment samePaymentPayment = createPayment(1L);
        Payment otherPaymentPayment = createPayment(2L, 1L);

        assertTrue(paymentPayment.hashCode() == samePaymentPayment.hashCode());
        assertFalse(paymentPayment.hashCode() == otherPaymentPayment.hashCode());
    }

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        Payment payment = createPayment(1L, 1L);
        String expected = "paymentId: " + payment.getId().toString();

        assertEquals(expected, payment.toString());
    }
}
