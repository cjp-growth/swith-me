package project.swithme.order.core.web.payment.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import project.swithme.order.common.exception.DomainException;

@Getter
public class InvalidOrderIdException extends DomainException {

    public InvalidOrderIdException() {
        super(HttpStatus.BAD_REQUEST, "올바른 주문 아이디를 입력해주세요.", "ORDER");
    }
}
