package project.swithme.payment.test.unittest;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import project.swithme.payment.core.facade.validator.PaymentValidator;

@DisplayName("[UnitTest] 주문 검증기 단위 테스트")
class PaymentValidatorTest {

    private BigDecimal orderPrice;
    private BigDecimal invalidOrderPrice;
    private PaymentValidator paymentValidator;

    @BeforeEach
    void setUp() {
        orderPrice = new BigDecimal(130_000L);
        invalidOrderPrice = new BigDecimal(100_000_000L);
        paymentValidator = new PaymentValidator();
    }

//    @ParameterizedTest
//    @ValueSource(strings = {"COMPLETE", "CANCEL", "REFUND"})
//    @DisplayName("주문 상태가 입금 대기가 아니라면 InvalidOrderStatusException이 발생한다.")
//    void invalid_order_status_validation_test(String parameter) {
//        Order order = createOrder(OrderStatus.valueOf(parameter));
//
//        assertThatThrownBy(() -> paymentValidator.validate(order, orderPrice))
//            .isExactlyInstanceOf(InvalidOrderStatusException.class)
//            .isInstanceOf(RuntimeException.class)
//            .hasMessage("올바르지 않은 주문 상태입니다.");
//    }

//    @Test
//    @DisplayName("주문 상태가 입금대기라면 예외가 발생하지 않는다.")
//    void valid_order_status_validation_test() {
//        Order order = createOrder(OrderStatus.PAYMENT_REQUEST);
//
//        assertDoesNotThrow(() -> paymentValidator.validate(order, orderPrice));
//    }

//    @Test
//    @DisplayName("주문 상태가 입금대기라면 예외가 발생하지 않는다.")
//    void invalid_price_validation_test() {
//        Order order = createOrder(OrderStatus.PAYMENT_REQUEST);
//
//        assertThatThrownBy(() -> paymentValidator.validate(order, invalidOrderPrice))
//            .isExactlyInstanceOf(InvalidPriceException.class)
//            .isInstanceOf(RuntimeException.class)
//            .hasMessage("올바른 가격을 입력해주세요");
//    }
}
