package project.swithme.payment.core.out.client.toss.adapter.response.success;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class EasyPayResponse {

    private String provider;
    private BigDecimal amount;
    private BigDecimal discountAmount;

    private EasyPayResponse() {
    }
}
