package project.swithme.order.common.response;

import java.time.Instant;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import project.swithme.order.common.exception.error.CodeAndMessage;

@Getter
public class ErrorResponse {

    private static final String DOMAIN = "PAYMENT";

    private final Instant time = Instant.now();
    private final int statusCode;
    private final String message;
    private final String domain = DOMAIN;

    public ErrorResponse(
        int statusCode,
        String message,
        String domain
    ) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorResponse(CodeAndMessage codeAndMessage) {
        this.statusCode = codeAndMessage.getStatusCode();
        this.message = codeAndMessage.getKrErrorMessage();
    }

    private ErrorResponse(
        HttpStatus httpStatus,
        String domain
    ) {
        this.statusCode = httpStatus.value();
        this.message = "서버 내부 오류입니다.";
    }

    public static ErrorResponse ofServer() {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "ORDER");
    }

    @Override
    public String toString() {
        return String.format(
            "time: %s, statusCode: %s, message: %s, domain: %s",
            time,
            statusCode,
            message,
            domain
        );
    }
}
