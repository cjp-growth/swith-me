package project.swithme.payment.core.out.client.toss.adapter.response.success;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class PaymentCardResponse {

    private BigDecimal amount;
    private String issuerCode;
    private String acquirerCode;
    private String number;
    private String installmentPlanMonths;
    private String approveNo;
    private Boolean useCardPoint;
    private String cardType;
    private String ownerType;
    private String acquireStatus;
    private Boolean isInterestFree;
    private String interestPayer;
    private String company;
    private String receiptUrl;

    private PaymentCardResponse() {
    }
}
