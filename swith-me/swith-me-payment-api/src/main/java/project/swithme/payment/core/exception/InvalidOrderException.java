package project.swithme.payment.core.exception;

import static project.study.support.codeandmessage.order.OrderErrorCodeAndMessage.INVALID_ORDER_STATUS;
import project.study.support.exception.DomainException;

public class InvalidOrderStatusException extends DomainException {

    public InvalidOrderStatusException() {
        super(INVALID_ORDER_STATUS);
    }
}
