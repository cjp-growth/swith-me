package project.swithme.payment.core.exception;

import org.springframework.http.HttpStatus;
import project.swithme.payment.common.exception.DomainException;

public class PaymentFailureException extends DomainException {

    public PaymentFailureException() {
        super(HttpStatus.NOT_FOUND, "결제", "PAYMENT");
    }
}
