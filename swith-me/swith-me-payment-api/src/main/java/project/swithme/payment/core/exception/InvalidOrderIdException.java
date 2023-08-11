package project.swithme.payment.core.exception;

import static project.study.support.codeandmessage.order.OrderCodeAndMessage.INVALID_ORDER_ID;
import project.study.support.exception.DomainException;

public class InvalidOrderIdException extends DomainException {

    public InvalidOrderIdException() {
        super(INVALID_ORDER_ID);
    }
}
