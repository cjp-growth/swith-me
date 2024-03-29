package project.swithme.payment.core.exception;

import static project.study.support.codeandmessage.order.OrderErrorCodeAndMessage.ORDER_NOT_FOUND;
import project.study.support.exception.DomainException;

public class OrderNotFoundException extends DomainException {

    public OrderNotFoundException() {
        super(ORDER_NOT_FOUND);
    }
}
