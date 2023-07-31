package project.swithme.order.core.web.payment.facade.validator;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.web.order.exception.InvalidOrderStatusException;
import project.swithme.order.core.web.payment.exception.InvalidPriceException;

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
