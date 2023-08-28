package project.swithme.notification.core.exception;

import static project.study.support.codeandmessage.order.OrderCodeAndMessage.INVALID_ORDER;
import project.study.support.exception.DomainException;

public class InvalidOrderException extends DomainException {

    public InvalidOrderException() {
        super(INVALID_ORDER);
    }
}
