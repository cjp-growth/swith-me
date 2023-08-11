package project.swithme.payment.core.exception;

import static project.study.support.codeandmessage.payment.PaymentCodeAndMessage.INVALID_PAYMENT_EXECUTION;
import project.study.support.codeandmessage.CodeAndMessage;
import project.study.support.exception.DomainException;

public class PaymentFailureException extends DomainException {

    public PaymentFailureException() {
        super(INVALID_PAYMENT_EXECUTION);
    }

    public PaymentFailureException(
        CodeAndMessage codeAndMessage,
        String detailMessage
    ) {
        super(codeAndMessage, detailMessage);
    }
}
