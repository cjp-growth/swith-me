package project.swithme.order.common.configuration.feign;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import feign.RequestInterceptor;
import java.util.Base64;
import java.util.Base64.Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.swithme.order.common.configuration.business.payment.TossProperties;

@Configuration
@RequiredArgsConstructor
public class BasicAuthConfiguration {

    private static final String BASIC_AUTH_PREFIX = "Basic ";
    private static final String DELIMETER = ":";

    private final TossProperties properties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String auth = properties.getSecretKey() + DELIMETER;
            byte[] encodedAuth = getEncoder()
                .encode(auth.getBytes(US_ASCII));
            String authHeader = BASIC_AUTH_PREFIX + new String(encodedAuth);
            requestTemplate.header(AUTHORIZATION, authHeader);
            requestTemplate.header(CONTENT_TYPE, APPLICATION_JSON);
            requestTemplate.header(ACCEPT, APPLICATION_JSON);
        };
    }

    private Encoder getEncoder() {
        return Base64.getEncoder();
    }
}
