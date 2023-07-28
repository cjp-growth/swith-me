package project.swithme.order.core.web.payment.facade.validator;

import org.springframework.stereotype.Component;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.payment.entity.Payment;
import project.swithme.order.core.web.order.exception.InvalidOrderStatusException;
import project.swithme.order.core.web.payment.exception.InvalidPriceException;

@Component
public class PaymentValidator {

    public void validate(
        Order order,
        Payment payment
    ) {
        if (!order.isValidStatus()) {
            throw new InvalidOrderStatusException();
        }
        if (!order.validatePrice(payment.getPrice())) {
            throw new InvalidPriceException();
        }
        payment.register(order.getId(), order.getUserId());
    }
}
