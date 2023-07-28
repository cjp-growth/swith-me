package project.swithme.order.core.web.payment.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import lombok.Getter;
import project.swithme.order.common.exception.DomainException;

@Getter
public class InvalidPriceException extends DomainException {

    public InvalidPriceException() {
        super(BAD_REQUEST, "올바른 가격을 입력해주세요", "PAYMENT");
    }
}
