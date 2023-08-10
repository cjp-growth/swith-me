package project.swithme.order.core.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import lombok.Getter;
import project.swithme.order.common.exception.DomainException;

@Getter
public class OrderNotFoundException extends DomainException {

    public OrderNotFoundException() {
        super(NOT_FOUND, "주문을 찾을 수 없습니다.", "ORDER");
    }
}
