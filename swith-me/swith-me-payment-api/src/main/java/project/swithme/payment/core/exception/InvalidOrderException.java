package project.swithme.payment.core.exception;

import project.study.support.codeandmessage.CodeAndMessage;
import project.study.support.exception.DomainException;

public class InvalidOrderException extends DomainException {

    public InvalidOrderException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage);
    }
}
