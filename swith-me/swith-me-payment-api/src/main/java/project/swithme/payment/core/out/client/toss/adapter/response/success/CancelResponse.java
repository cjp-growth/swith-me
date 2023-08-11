package project.swithme.payment.core.out.client.toss.adapter.response.success;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class CancelResponse {

    private BigDecimal cancelAmount;
    private String cancelReason;
    private BigDecimal taxFreeAmount;
    private Integer taxExemptionAmount;
    private BigDecimal refundableAmount;
    private BigDecimal easyPayDiscountAmount;
    private String canceledAt;
    private String transactionKey;
    private String receiptKey;

    private CancelResponse() {
    }
}
