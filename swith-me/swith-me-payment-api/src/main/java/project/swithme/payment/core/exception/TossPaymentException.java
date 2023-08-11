package project.swithme.payment.core.exception;

import lombok.Getter;
import project.study.support.codeandmessage.CodeAndMessage;
import project.study.support.exception.OutPortException;

@Getter
public class TossPaymentException extends OutPortException {

    public TossPaymentException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage);
    }

    public TossPaymentException(
        CodeAndMessage codeAndMessage,
        String detailMessage
    ) {
        super(codeAndMessage, detailMessage);
    }
}
