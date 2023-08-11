package project.swithme.payment.core.facade.validator;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import project.swithme.payment.common.command.OrderValidationCommand;
import project.swithme.payment.core.exception.InvalidOrderStatusException;
import project.swithme.payment.core.exception.InvalidPriceException;

@Component
public class PaymentValidator {

    public void validate(
        OrderValidationCommand command,
        BigDecimal amount
    ) {
        if (!command.isValidStatus()) {
            throw new InvalidOrderStatusException();
        }
        if (!command.validatePrice(amount)) {
            throw new InvalidPriceException();
        }
    }
}
