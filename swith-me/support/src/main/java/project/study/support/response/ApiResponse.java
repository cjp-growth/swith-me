package project.study.support.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> extends ResponseEntity<ResponsePayload<T>> {

    private static final String CREATED = "CREATED";

    public ApiResponse(T data) {
        super(new ResponsePayload<>(data, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    public ApiResponse(
        T data,
        HttpStatus status
    ) {
        super(new ResponsePayload<>(data, status), status);
    }

    public ApiResponse(
        T data,
        HttpStatus status,
        String message
    ) {
        super(new ResponsePayload<>(data, status, message), status);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(data);
    }

    public static <T> ApiResponse<T> created(
        T data,
        HttpStatus status
    ) {
        return new ApiResponse<>(data, status, CREATED);
    }

    public static <T> ApiResponse<T> of(
        T data,
        HttpStatus status
    ) {
        return new ApiResponse<>(data, status);
    }
}
