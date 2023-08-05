package project.swithme.order.common.aop;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import project.swithme.order.common.aop.log.CommonRequestLog;

@Slf4j
@Aspect
@Order(1)
@Component
@RequiredArgsConstructor
public class LogAspect {

    private static final String TRACE_ID = "traceId";

    @Before("bean(*API))")
    public void beforeCallAPI(JoinPoint joinPoint) {
        CommonRequestLog commonRequestLog = new CommonRequestLog(getRequest());
        log.info(
            "[### PAYMENT_REQUEST] -----x> traceId: {}, \nrequest:{}, \nargs: {}",
            getTraceId(), commonRequestLog, Arrays.toString(joinPoint.getArgs())
        );
    }

    @AfterReturning(value = "bean(*API)", returning = "result")
    public void afterCallAPI(
        JoinPoint joinPoint,
        Object result
    ) {
        log.info(
            "[### PAYMENT_RESPONSE] <x----- traceId: {}, \nreturn: {}",
            getTraceId(), result
        );
    }

    @Before("bean(*Facade))")
    public void beforeCallFacade(JoinPoint joinPoint) {
        log.info(
            "[### PAYMENT_FACADE] -----x> traceId: {}, \nargs: {}",
            getTraceId(), Arrays.toString(joinPoint.getArgs())
        );
    }

    @AfterReturning(value = "bean(*Facade)", returning = "result")
    public void afterCallFacade(
        JoinPoint joinPoint,
        Object result
    ) {
        log.info(
            "[### PAYMENT_FACADE] <x----- traceId: {}, \nreturn: {}",
            getTraceId(), result
        );
    }

    @Before("bean(*Service))")
    public void beforeCallService(JoinPoint joinPoint) {
        log.info(
            "[### PAYMENT_SERVICE] -----x> traceId: {}, \nargs:{}",
            getTraceId(), Arrays.toString(joinPoint.getArgs())
        );
    }

    @AfterReturning(value = "bean(*Service)", returning = "result")
    public void afterCallService(
        JoinPoint joinPoint,
        Object result
    ) {
        log.info(
            "[### PAYMENT_SERVICE] <x----- traceId: {}, \nreturn: {}",
            getTraceId(), result
        );
    }

    private String getTraceId() {
        return MDC.get(TRACE_ID);
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}