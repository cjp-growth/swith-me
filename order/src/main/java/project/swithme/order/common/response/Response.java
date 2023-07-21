package project.swithme.order.common.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class Response<T> {

    private final T data;
    private final int code;
    private final String message;
    private final LocalDateTime time = LocalDateTime.now();

    public Response(
            T data,
            HttpStatus status
    ) {
        this.data = data;
        this.code = status.value();
        this.message = "Success";
    }

    public Response(
            T data,
            HttpStatus status,
            String message
    ) {
        this.data = data;
        this.code = status.value();
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format(
                "Response: [data: %s, code: %s, message: %s, time:%s]", data, code, message, time
        );
    }
}