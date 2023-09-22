package project.swithme.payment.test.unittest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.swithme.domain.core.order.entity.OrderStatus;
import project.swithme.payment.common.command.OrderValidationCommand;
import project.swithme.payment.core.exception.InvalidOrderException;
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

    @ParameterizedTest
    @ValueSource(strings = {"COMPLETE", "CANCEL", "REFUND"})
    @DisplayName("주문 상태가 입금 대기가 아니라면 InvalidOrderStatusException이 발생한다.")
    void invalid_order_status_validation_test(String parameter) {
        OrderValidationCommand command = new OrderValidationCommand(
            1L,
            1L,
            UUID.randomUUID(),
            OrderStatus.findStatus(parameter),
            BigDecimal.valueOf(100_000L)
        );

        assertThatThrownBy(() -> paymentValidator.validate(command, BigDecimal.valueOf(100_000L)))
            .isExactlyInstanceOf(InvalidOrderException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바르지 않은 주문 상태입니다.");
    }

    @Test
    @DisplayName("주문 상태가 입금대기라면 예외가 발생하지 않는다.")
    void valid_order_status_validation_test() {
        OrderValidationCommand command = new OrderValidationCommand(
            1L,
            1L,
            UUID.randomUUID(),
            OrderStatus.PAYMENT_REQUEST,
            BigDecimal.valueOf(100_000L)
        );

        assertDoesNotThrow(() -> paymentValidator.validate(command, BigDecimal.valueOf(100_000L)));
    }

    @Test
    @DisplayName("주문 가격이 다르다면 InvalidPriceException이 발생한다.")
    void invalid_price_validation_test() {
        OrderValidationCommand command = new OrderValidationCommand(
            1L,
            1L,
            UUID.randomUUID(),
            OrderStatus.PAYMENT_REQUEST,
            BigDecimal.valueOf(100_000L)
        );

        assertThatThrownBy(() -> paymentValidator.validate(command, BigDecimal.valueOf(110_000L)))
            .isExactlyInstanceOf(InvalidOrderException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("올바른 주문 가격을 입력해주세요.");
    }
}
