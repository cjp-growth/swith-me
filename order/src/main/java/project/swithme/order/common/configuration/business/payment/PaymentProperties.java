package project.swithme.order.common.configuration.business.payment;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class PaymentProperties {

    private final Map<String, String> factory;

    @Value("${payment.toss.baseUrl}")
    private String baseUrl;

    @Value("${payment.toss.path}")
    private String path;

    @Value("${payment.toss.secretKey}")
    private String secretKey;

    public PaymentProperties() {
        factory = new HashMap<>();
        factory.put("baseUrl", baseUrl);
        factory.put("path", path);
    }
}
