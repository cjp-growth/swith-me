package project.swithme.order.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DomainException extends RuntimeException {

    private final HttpStatus status;
    private final int code;
    private final String errorMessage;
    private final String domain;

    public DomainException(
        HttpStatus status,
        String errorMessage,
        String domain
    ) {
        super(errorMessage);
        this.status = status;
        this.code = status.value();
        this.errorMessage = errorMessage;
        this.domain = domain;
    }
}
