package project.swithme.payment.common.configuration.filter;

import static com.fasterxml.uuid.Generators.timeBasedGenerator;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class MDCLoggingFilter implements Filter {

    // TODO. API Gateway(Nginx, SpringCloud 등)에서 UUID를 주는 경우
    @Override
    public void doFilter(
        final ServletRequest servletRequest,
        final ServletResponse servletResponse,
        final FilterChain filterChain
    ) throws IOException, ServletException {
        final UUID uuid = timeBasedGenerator().generate();

        MDC.put("traceId", uuid.toString());

        long startTime = System.currentTimeMillis();
        log.info("[### PAYMENT_START] -----x> traceId: {}", uuid);
        filterChain.doFilter(servletRequest, servletResponse);

        long endTime = System.currentTimeMillis();
        long latency = endTime - startTime;
        log.info("[### PAYMENT_END] <x----- traceId: {}, in-server-latency: {}ms", uuid, latency);
        MDC.clear();
    }
}
