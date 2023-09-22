package project.swithme.notification.core.web.exception;

import static project.study.support.codeandmessage.order.OrderErrorCodeAndMessage.ORDER_NOT_FOUND;
import project.study.support.exception.DomainException;

public class InvalidOrderException extends DomainException {

    public InvalidOrderException() {
        super(ORDER_NOT_FOUND);
    }
}
