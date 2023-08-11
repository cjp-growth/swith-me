package project.swithme.payment.core.out.client.toss.adapter.response.success;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class DiscountResponse {

    private BigDecimal amount;

    private DiscountResponse() {
    }

}
