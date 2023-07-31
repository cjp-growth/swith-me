package project.swithme.order.core.web.payment.out.adapter.response;

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
