package project.swithme.payment.core.facade.validator;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.payment.core.exception.InvalidPriceException;

@Component
public class PaymentValidator {

    public void validate(
        Order order,
        BigDecimal amount
    ) {
        if (!order.isValidStatus()) {
            throw new InvalidOrderStatusException();
        }
        if (!order.validatePrice(amount)) {
            throw new InvalidPriceException();
        }
    }
}
