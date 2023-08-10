package project.swithme.payment.common.response;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseBody<T> {

    private final T data;
    private final int code;
    private final String message;
    private final LocalDateTime time = LocalDateTime.now();

    public ResponseBody(
        T data,
        HttpStatus status
    ) {
        this.data = data;
        this.code = status.value();
        this.message = "Success";
    }

    public ResponseBody(
        T data,
        HttpStatus status,
        String message
    ) {
        this.data = data;
        this.code = status.value();
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format(
            "data: %s, code: %s, message: %s, time:%s",
            data, code, message, time
        );
    }
}