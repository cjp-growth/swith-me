package project.study.support.exception;

import lombok.Getter;
import project.study.support.codeandmessage.CodeAndMessage;

@Getter
public class OutPortException extends RuntimeException {

    private CodeAndMessage codeAndMessage;
    private String detailMessage;

    public OutPortException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage.getKrErrorMessage());
        this.codeAndMessage = codeAndMessage;
    }

    public OutPortException(
        CodeAndMessage codeAndMessage,
        String detailMessage
    ) {
        super(codeAndMessage.getKrErrorMessage());
        this.codeAndMessage = codeAndMessage;
        this.detailMessage = detailMessage;
    }

    public int getStatusCode() {
        return codeAndMessage.getStatusCode();
    }

    public String getErrorMessage() {
        if (codeAndMessage != null) {
            return codeAndMessage.getKrErrorMessage();
        }
        return getMessage();
    }
}
