package project.swithme.payment.core.out.client.toss.adapter.response.success;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class CashReceiptsResponse {

    private String receiptKey;

    private String orderId;
    private String orderName;
    private String type;
    private String issueNumber;
    private String receiptUrl;
    private String businessNumber;
    private String transactionType;
    private BigDecimal amount;
    private BigDecimal taxFreeAmount;
    private String issueStatus;
    private FailureResponse failure;
    private String customerIdentityNumber;
    private String requestedAt;

    private CashReceiptsResponse() {
    }
}
