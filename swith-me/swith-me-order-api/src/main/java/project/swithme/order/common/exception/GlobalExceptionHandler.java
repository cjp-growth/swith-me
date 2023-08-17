package project.swithme.order.common.exception;

import static project.study.support.codeandmessage.common.CommonErrorCodeAndMessage.INVALID_PATH_VARIABLE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import project.study.support.exception.DomainException;
import project.study.support.response.failure.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String DOMAIN = "ORDER";

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> resolveDomainException(DomainException exception) {
        return ResponseEntity.status(exception.getStatusCode())
            .body(ErrorResponse.of(exception));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> resolveMethodArgumentTypeMismatchException(
        MethodArgumentTypeMismatchException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(ErrorResponse.of(INVALID_PATH_VARIABLE));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> catchUnResolvedException(Exception exception) {
        ErrorResponse serverError = ErrorResponse.ofServer();
        return ResponseEntity.status(serverError.getCode())
            .body(serverError);
    }
}
