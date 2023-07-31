package project.swithme.order.core.web.payment.out.adapter.response;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class DiscountResponse {

    private BigDecimal amount;

    private DiscountResponse() {
    }

}
