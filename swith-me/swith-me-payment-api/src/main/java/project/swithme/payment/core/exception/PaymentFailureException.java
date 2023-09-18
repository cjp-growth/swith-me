package project.swithme.payment.core.exception;

import project.study.support.codeandmessage.CodeAndMessage;
import project.study.support.exception.DomainException;

public class PaymentFailureException extends DomainException {

    public PaymentFailureException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage);
    }
}
