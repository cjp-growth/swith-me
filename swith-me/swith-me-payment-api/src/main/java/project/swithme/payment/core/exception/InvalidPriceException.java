package project.swithme.payment.core.exception;

import static project.study.support.codeandmessage.order.OrderCodeAndMessage.INVALID_PRICE;
import project.study.support.exception.DomainException;

public class InvalidPriceException extends DomainException {

    public InvalidPriceException() {
        super(INVALID_PRICE);
    }
}
