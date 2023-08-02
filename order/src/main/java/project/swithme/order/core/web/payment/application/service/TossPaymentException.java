package project.swithme.order.core.web.payment.application.service;

import lombok.Getter;
import project.swithme.order.common.exception.DomainException;
import project.swithme.order.common.exception.error.CodeAndMessage;

@Getter
public class TossPaymentException extends DomainException {

    public TossPaymentException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage);
    }
}
