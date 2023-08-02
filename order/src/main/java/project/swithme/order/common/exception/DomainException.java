package project.swithme.order.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import project.swithme.order.common.exception.error.CodeAndMessage;

@Getter
public class DomainException extends RuntimeException {

    private HttpStatus status;
    private CodeAndMessage codeAndMessage;
    private final int statusCode;
    private final String errorMessage;

    public DomainException(
        HttpStatus status,
        String errorMessage,
        String domain
    ) {
        super(errorMessage);
        this.status = HttpStatus.valueOf(status.value());
        this.statusCode = status.value();
        this.errorMessage = errorMessage;
    }

    public DomainException(CodeAndMessage codeAndMessage) {
        super(codeAndMessage.getKrErrorMessage());
        this.statusCode = codeAndMessage.getStatusCode();
        this.status = HttpStatus.valueOf(statusCode);
        this.codeAndMessage = codeAndMessage;
        this.errorMessage = codeAndMessage.getKrErrorMessage();
    }
}
