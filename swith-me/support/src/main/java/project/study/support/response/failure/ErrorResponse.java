package project.study.support.response.failure;

import lombok.Getter;
import project.study.support.codeandmessage.CodeAndMessage;
import project.study.support.codeandmessage.common.CommonErrorCodeAndMessage;
import project.study.support.exception.CommonException;
import project.study.support.exception.DomainException;
import project.study.support.exception.OutPortException;

@Getter
public class ErrorResponse {

    private final int code;
    private final String message;

    private ErrorResponse(
        int code,
        String message
    ) {
        this.code = code;
        this.message = message;
    }

    private ErrorResponse(CodeAndMessage codeAndMessage) {
        this.code = codeAndMessage.getStatusCode();
        this.message = codeAndMessage.getKrErrorMessage();
    }

    public static ErrorResponse of(CodeAndMessage codeAndMessage) {
        return new ErrorResponse(codeAndMessage);
    }

    public static ErrorResponse of(CommonException exception) {
        return new ErrorResponse(exception.getStatusCode(), exception.getMessage());
    }

    public static ErrorResponse of(DomainException exception) {
        return new ErrorResponse(exception.getStatusCode(), exception.getMessage());
    }

    public static ErrorResponse of(OutPortException exception) {
        return new ErrorResponse(exception.getStatusCode(), exception.getMessage());
    }

    public static ErrorResponse ofServer() {
        return new ErrorResponse(CommonErrorCodeAndMessage.INTERNAL_SERVER_ERROR);
    }

    @Override
    public String toString() {
        return String.format("code: %s, message: %s", code, message);
    }
}
