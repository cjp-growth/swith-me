package project.swithme.payment.core.application.service;

import lombok.Getter;
import project.swithme.payment.common.exception.DomainException;
import project.swithme.payment.common.response.codeandmessage.CodeAndMessage;

@Getter
public class TossPaymentException extends DomainException {

    public TossPaymentException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage);
    }
}
