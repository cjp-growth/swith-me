package project.study.support.exception;

import lombok.Getter;
import project.study.support.codeandmessage.CodeAndMessage;

@Getter
public class DomainException extends RuntimeException {

    private CodeAndMessage codeAndMessage;
    private String detailMessage;

    public DomainException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage.getKrErrorMessage());
        this.codeAndMessage = codeAndMessage;
    }

    public DomainException(
        CodeAndMessage codeAndMessage,
        String detailMessage
    ) {
        super(codeAndMessage.getKrErrorMessage());
        this.codeAndMessage = codeAndMessage;
        this.detailMessage = detailMessage;
    }

    public DomainException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return codeAndMessage.getStatusCode();
    }

    public String getMessage() {
        return codeAndMessage.getKrErrorMessage();
    }
}
