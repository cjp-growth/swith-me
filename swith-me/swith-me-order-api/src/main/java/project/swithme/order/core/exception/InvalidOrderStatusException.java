package project.swithme.order.core.exception;

import static project.study.support.codeandmessage.order.OrderCodeAndMessage.INVALID_ORDER_STATUS;
import project.study.support.exception.DomainException;

public class InvalidOrderStatusException extends DomainException {

    public InvalidOrderStatusException() {
        super(INVALID_ORDER_STATUS);
    }
}
