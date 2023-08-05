package project.swithme.order.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.swithme.order.common.aop.log.ErrorLog;
import project.swithme.order.common.aop.log.Field;
import project.swithme.order.common.exception.error.CommonCodeAndMessage;
import project.swithme.order.common.response.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String TRACE_ID = "traceId";

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> catchDomainException(DomainException domainException) {
        ErrorResponse data = createResponse(domainException);
        log.error("Error: {}", createResponse(domainException));
        return new ResponseEntity<>(data, domainException.getStatus());
    }

    // TODO. 미해결
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void catchHttpMessageNotReadableException(
        HttpMessageNotReadableException httpMessageNotReadableException
    ) {
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ErrorResponse> catchInvalidParameterException(
        InvalidParameterException invalidParameterException
    ) {
        ErrorLog errorLog = createLog(
            CommonCodeAndMessage.BAD_REQUEST,
            invalidParameterException.getFields()
        );
        log.error("[### PAYMENT_ERROR] -----x> traceId: {}, {}", getTraceId(), errorLog);
        return new ResponseEntity<>(ErrorResponse.ofBadRequest(), BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> catchUnresolvedException() {
        ErrorResponse data = ErrorResponse.ofServer();
        log.error("### PAYMENT_ERROR -----x> traceId: {}, {}", getTraceId(), data);
        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse createResponse(DomainException domainException) {
        return new ErrorResponse(domainException.getCodeAndMessage());
    }

    private ErrorLog createLog(
        CommonCodeAndMessage codeAndMessage,
        List<Field> fields
    ) {
        return new ErrorLog(
            codeAndMessage.name(),
            codeAndMessage.getStatusCode(),
            codeAndMessage.getKrErrorMessage(),
            fields
        );
    }

    private String getTraceId() {
        return MDC.get(TRACE_ID);
    }
}
