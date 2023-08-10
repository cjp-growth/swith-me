package project.swithme.payment.core.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import lombok.Getter;
import project.swithme.payment.common.exception.DomainException;

@Getter
public class InvalidPriceException extends DomainException {

    public InvalidPriceException() {
        super(BAD_REQUEST, "올바른 가격을 입력해주세요", "PAYMENT");
    }
}
