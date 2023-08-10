package project.swithme.payment.core.out.adapter.response;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class CashReceiptResponse {

    private String type;
    private String receiptKey;
    private String issueNumber;
    private String receiptUrl;
    private BigDecimal amount;
    private BigDecimal taxFreeAmount;

    private CashReceiptResponse() {
    }
}
