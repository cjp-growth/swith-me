package project.swithme.order.common.exception.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import project.swithme.order.common.exception.DomainException;

@Getter
public class ErrorResponse {

    private final int code;
    private final String message;
    private final String domain;

    public ErrorResponse(DomainException domainException) {
        this.code = domainException.getCode();
        this.message = domainException.getErrorMessage();
        this.domain = domainException.getDomain();
    }

    private ErrorResponse(
        HttpStatus httpStatus,
        String domain
    ) {
        this.code = httpStatus.value();
        this.message = "서버 내부 오류입니다.";
        this.domain = domain;
    }

    public static ErrorResponse ofServer() {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "ORDER");
    }
}
