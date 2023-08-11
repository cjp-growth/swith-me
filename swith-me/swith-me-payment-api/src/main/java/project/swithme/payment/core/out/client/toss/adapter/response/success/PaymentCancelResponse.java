package project.swithme.payment.core.out.client.toss.adapter.response.success;

import lombok.Getter;

@Getter
public class PaymentCancelResponse {

    private String errorCode;
    private String errorMsg;
    private String orderId;

    private PaymentCancelResponse() {
    }

    public PaymentCancelResponse(
        String errorCode,
        String errorMsg,
        String orderId
    ) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.orderId = orderId;
    }
}
