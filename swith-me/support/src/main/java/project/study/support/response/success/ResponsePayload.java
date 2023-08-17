package project.study.support.response.success;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponsePayload<T> {

    private final T data;
    private final int code;
    private final String message;

    public ResponsePayload(
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
            "data: %s, code: %s, message: %s",
            data, code, message
        );
    }
}
