package project.swithme.payment.common.mockresponse;

import java.math.BigDecimal;

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

    public BigDecimal getCancelAmount() {
        return cancelAmount;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public BigDecimal getTaxFreeAmount() {
        return taxFreeAmount;
    }

    public Integer getTaxExemptionAmount() {
        return taxExemptionAmount;
    }

    public BigDecimal getRefundableAmount() {
        return refundableAmount;
    }

    public BigDecimal getEasyPayDiscountAmount() {
        return easyPayDiscountAmount;
    }

    public String getCanceledAt() {
        return canceledAt;
    }

    public String getTransactionKey() {
        return transactionKey;
    }

    public String getReceiptKey() {
        return receiptKey;
    }
}
