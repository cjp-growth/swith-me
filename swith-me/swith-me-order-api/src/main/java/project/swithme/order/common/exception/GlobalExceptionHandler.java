package project.swithme.order.common.exception;

import static project.study.support.codeandmessage.common.CommonErrorCodeAndMessage.INVALID_PATH_VARIABLE;
import static project.study.support.codeandmessage.common.CommonErrorCodeAndMessage.INVALID_QUERY_PARAMETERS;
import static project.study.support.codeandmessage.common.CommonErrorCodeAndMessage.URL_NOTFOUND;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import project.study.support.exception.DomainException;
import project.study.support.exception.InvalidParameterException;
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

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ErrorResponse> resolveInvalidParameterExceptionException(
        InvalidParameterException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(ErrorResponse.of(INVALID_QUERY_PARAMETERS));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> invalidMissingServletRequestParameterExceptionException(
        MissingServletRequestParameterException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
            .body(ErrorResponse.of(INVALID_QUERY_PARAMETERS));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> resolveNoHandlerFoundException(
        NoHandlerFoundException exception
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
            .body(ErrorResponse.of(URL_NOTFOUND));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> catchUnResolvedException(Exception exception) {
        ErrorResponse serverError = ErrorResponse.ofServer();
        return ResponseEntity.status(serverError.getCode())
            .body(serverError);
    }
}
