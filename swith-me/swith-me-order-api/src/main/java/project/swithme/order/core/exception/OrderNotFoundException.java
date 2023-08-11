package project.swithme.order.core.exception;

import static project.study.support.codeandmessage.order.OrderCodeAndMessage.ORDER_NOT_FOUND;
import project.study.support.exception.DomainException;

public class OrderNotFoundException extends DomainException {

    public OrderNotFoundException() {
        super(ORDER_NOT_FOUND);
    }
}
