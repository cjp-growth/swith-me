package project.swithme.payment.core.facade.validator;

import static project.study.support.codeandmessage.order.OrderErrorCodeAndMessage.INVALID_ORDER_STATUS;
import static project.study.support.codeandmessage.order.OrderErrorCodeAndMessage.INVALID_PRICE;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import project.swithme.payment.common.command.OrderValidationCommand;
import project.swithme.payment.core.exception.InvalidOrderException;

@Component
public class PaymentValidator {

    public void validate(
        OrderValidationCommand command,
        BigDecimal amount
    ) {
        if (!command.isValidStatus()) {
            throw new InvalidOrderException(INVALID_ORDER_STATUS);
        }
        if (!command.validatePrice(amount)) {
            throw new InvalidOrderException(INVALID_PRICE);
        }
    }
}
