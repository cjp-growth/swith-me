package project.swithme.order.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

    private final int code;
    private final String message;
    private final String domain;

    public ErrorResponse(
        int code,
        String message,
        String domain
    ) {
        this.code = code;
        this.message = message;
        this.domain = domain;
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
