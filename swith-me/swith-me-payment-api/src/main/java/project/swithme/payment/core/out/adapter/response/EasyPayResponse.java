package project.swithme.payment.core.out.adapter.response;

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
