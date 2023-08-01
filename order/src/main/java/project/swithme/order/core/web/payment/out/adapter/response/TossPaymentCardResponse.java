package project.swithme.order.core.web.payment.out.adapter.response;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class TossPaymentCardResponse {

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

    private TossPaymentCardResponse() {
    }
}
