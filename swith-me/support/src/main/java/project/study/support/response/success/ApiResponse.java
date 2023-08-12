package project.study.support.response.success;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.study.support.codeandmessage.CodeAndMessage;

public class ApiResponse<T> extends ResponseEntity<ResponsePayload<T>> {

    public static Object EMPTY_BODY = null;

    public ApiResponse(
        T data,
        HttpStatus status,
        String message
    ) {
        super(new ResponsePayload<>(data, status, message), status);
    }

    public static <T> ApiResponse<T> of(CodeAndMessage codeAndMessage) {
        return new ApiResponse<>(
            null,
            HttpStatus.valueOf(codeAndMessage.getStatusCode()),
            codeAndMessage.getKrErrorMessage()
        );
    }

    public static <T> ApiResponse<T> of(
        T data,
        CodeAndMessage codeAndMessage
    ) {
        return new ApiResponse<>(
            data,
            HttpStatus.valueOf(codeAndMessage.getStatusCode()),
            codeAndMessage.getKrErrorMessage()
        );
    }

    @Override
    public String toString() {
        return String.format(
            "data: %s, code: %s, message: %s",
            getBody().getData(),
            getStatusCode(),
            getBody().getMessage()
        );
    }
}
