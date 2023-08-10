package project.swithme.payment.core.application.service;

import lombok.Getter;
import project.study.support.exception.DomainException;
import project.study.support.response.codeandmessage.CodeAndMessage;

@Getter
public class TossPaymentException extends DomainException {

    public TossPaymentException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage);
    }
}
