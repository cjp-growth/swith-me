package project.swithme.payment.core.facade.validator;

import project.study.support.exception.DomainException;

public class InvalidOrderStatusException extends DomainException {

    public InvalidOrderStatusException() {
        super(null, "올바르지 않은 주문 상태입니다.", null);
    }
}
