package project.swithme.order.core.exception;

import org.springframework.http.HttpStatus;
import project.study.support.exception.DomainException;

public class InvalidOrderStatusException extends DomainException {

    public InvalidOrderStatusException() {
        super(HttpStatus.BAD_REQUEST, "올바르지 않은 주문 상태입니다.", "ORDER");
    }
}
