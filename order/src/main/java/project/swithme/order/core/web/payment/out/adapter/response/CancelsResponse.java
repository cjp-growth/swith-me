package project.swithme.order.core.web.payment.out.adapter.response;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class CancelsResponse {

    private BigDecimal cancelAmount;
    private String cancelReason;
    private BigDecimal taxFreeAmount;
    private Integer taxExemptionAmount;
    private BigDecimal refundableAmount;
    private BigDecimal easyPayDiscountAmount;
    private String canceledAt;
    private String transactionKey;
    private String receiptKey;

    private CancelsResponse() {
    }
}
