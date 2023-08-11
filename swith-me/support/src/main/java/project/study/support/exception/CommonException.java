package project.study.support.exception;

import java.util.List;
import lombok.Getter;
import project.study.support.codeandmessage.CodeAndMessage;
import project.study.support.log.Field;

@Getter
public class CommonException extends RuntimeException {

    private final CodeAndMessage codeAndMessage;
    private List<Field> fields;

    public CommonException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage.getKrErrorMessage());
        this.codeAndMessage = codeAndMessage;
    }

    public CommonException(
        CodeAndMessage codeAndMessage,
        List<Field> fields
    ) {
        super(codeAndMessage.getKrErrorMessage());
        this.codeAndMessage = codeAndMessage;
        this.fields = fields;
    }

    public int getStatusCode() {
        return codeAndMessage.getStatusCode();
    }

    public String getMessage() {
        return codeAndMessage.getKrErrorMessage();
    }
}
