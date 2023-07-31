package project.swithme.order.core.web.payment.exception;

import org.springframework.http.HttpStatus;
import project.swithme.order.common.exception.DomainException;

public class PaymentFailureException extends DomainException {

    public PaymentFailureException() {
        super(HttpStatus.NOT_FOUND, "결제", "PAYMENT");
    }
}
