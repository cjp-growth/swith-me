package project.swithme.payment.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.study.support.exception.CommonException;
import project.study.support.exception.DomainException;
import project.study.support.exception.OutPortException;
import project.study.support.response.failure.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String DOMAIN = "PAYMENT";

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> resolveCommonException(CommonException exception) {
        return ResponseEntity.status(exception.getStatusCode())
            .body(ErrorResponse.of(exception));
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> resolveDomainException(DomainException exception) {
        return ResponseEntity.status(exception.getStatusCode())
            .body(ErrorResponse.of(exception));
    }

    @ExceptionHandler(OutPortException.class)
    public ResponseEntity<ErrorResponse> resolveOutPortException(OutPortException exception) {
        logOutPortException(exception);
        return ResponseEntity.status(exception.getStatusCode())
            .body(ErrorResponse.of(exception));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> catchUnResolvedException(Exception exception) {
        ErrorResponse serverError = ErrorResponse.ofServer();
        return ResponseEntity.status(serverError.getCode())
            .body(serverError);
    }

    private void logOutPortException(OutPortException exception) {
        log.error("code: {}, message: {}",
            exception.getStatusCode(),
            exception.getErrorMessage()
        );
    }
}
