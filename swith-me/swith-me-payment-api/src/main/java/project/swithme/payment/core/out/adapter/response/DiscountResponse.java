package project.swithme.payment.core.out.adapter.response;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class DiscountResponse {

    private BigDecimal amount;

    private DiscountResponse() {
    }

}
