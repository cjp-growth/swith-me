package project.swithme.payment.common.exception;

import static project.study.support.codeandmessage.common.CommonErrorCodeAndMessage.URL_NOTFOUND;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import project.study.support.codeandmessage.common.CommonErrorCodeAndMessage;
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

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> resolveMissingServletRequestParameterException(
        MissingServletRequestParameterException exception
    ) {
        return ResponseEntity.status(400)
            .body(ErrorResponse.of(CommonErrorCodeAndMessage.INVALID_REQUEST));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> resolveNoHandlerFoundException(
        NoHandlerFoundException exception
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
            .body(ErrorResponse.of(URL_NOTFOUND));
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
