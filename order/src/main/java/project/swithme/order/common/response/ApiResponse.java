package project.swithme.order.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> extends ResponseEntity<Response<T>> {

    private static final String CREATED = "CREATED";

    public ApiResponse(
            T data,
            HttpStatus status
    ) {
        super(new Response<>(data, status), status);
    }

    public ApiResponse(
            T data,
            HttpStatus status,
            String message
    ) {
        super(new Response<>(data, status, message), status);
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
