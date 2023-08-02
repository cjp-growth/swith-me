package project.swithme.order.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.swithme.order.common.response.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> catchTest(DomainException domainException) {
        ErrorResponse data = createResponse(domainException);
        log.error("Error: {}", data);
        return new ResponseEntity<>(data, domainException.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> catchUnresolvedException() {
        ErrorResponse data = ErrorResponse.ofServer();
        log.error("Error: {}", data);
        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse createResponse(DomainException domainException) {
        return new ErrorResponse(domainException.getCodeAndMessage());
    }
}
