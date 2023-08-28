package project.swithme.notification.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.study.support.exception.DomainException;
import project.study.support.response.failure.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String DOMAIN = "NOTIFICATION";

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> resolveDomainException(DomainException exception) {
        return ResponseEntity.status(exception.getStatusCode())
            .body(ErrorResponse.of(exception));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> catchUnResolvedException(Exception exception) {
        ErrorResponse serverError = ErrorResponse.ofServer();
        return ResponseEntity.status(serverError.getCode())
            .body(serverError);
    }
}
