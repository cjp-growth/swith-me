package project.swithme.order.common.aop.log;

import java.time.Instant;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorLog {

    private static final String DOMAIN = "PAYMENT";
    private String httpStatus;
    private int statusCode;
    private String message;
    private List<Field> errorFields;
    private Instant time = Instant.now();

    @Builder
    public ErrorLog(
        String httpStatus,
        int statusCode,
        String message,
        List<Field> errorFields
    ) {
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.message = message;
        this.errorFields = errorFields;
    }

    @Override
    public String toString() {
        return String.format(
            "domain: %s, fields: %s, httpStatus: %s, statusCode: %s, message: %s, time: %s",
            DOMAIN, errorFields, httpStatus, statusCode, message, time
        );
    }
}
