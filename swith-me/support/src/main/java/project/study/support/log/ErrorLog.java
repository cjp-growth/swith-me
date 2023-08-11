package project.study.support.log;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorLog {

    private String domain;
    private int statusCode;
    private String message;
    private String detailMessage;
    private Instant time = Instant.now();

    @Builder
    public ErrorLog(
        String domain,
        int statusCode,
        String message,
        String detailMessage
    ) {
        this.domain = domain;
        this.statusCode = statusCode;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    @Override
    public String toString() {
        return String.format(
            "domain: %s, statusCode: %s, message: %s, detailMessage: %s, time: %s",
            domain, statusCode, message, detailMessage, time
        );
    }
}
