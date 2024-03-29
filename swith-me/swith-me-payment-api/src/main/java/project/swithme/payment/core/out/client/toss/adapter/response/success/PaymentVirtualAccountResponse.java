package project.swithme.payment.core.out.client.toss.adapter.response.success;

import java.beans.Transient;
import lombok.Getter;

@Getter
public class PaymentVirtualAccountResponse {

    private String accountType;
    private String accountNumber;
    private String bankCode;
    private String customerName;
    private String dueDate;
    private String refundStatus;
    private Boolean expired;
    private String settlementStatus;
    private RefundReceiveAccountResponse refundReceiveAccount;

    private PaymentVirtualAccountResponse() {
    }

    @Transient
    public String getBankCode() {
        if (refundReceiveAccount == null) {
            return null;
        }
        return refundReceiveAccount.getBankCode();
    }

    @Transient
    public String getAccountNumber() {
        if (refundReceiveAccount == null) {
            return null;
        }
        return refundReceiveAccount.getAccountNumber();
    }

    @Transient
    public String getHolderName() {
        if (refundReceiveAccount == null) {
            return null;
        }
        return refundReceiveAccount.getHolderName();
    }
}
