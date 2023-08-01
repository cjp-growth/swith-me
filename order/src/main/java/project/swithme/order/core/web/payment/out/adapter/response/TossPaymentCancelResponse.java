package project.swithme.order.core.web.payment.out.adapter.response;

import lombok.Getter;

@Getter
public class TossPaymentCancelResponse {

    private String errorCode;
    private String errorMsg;
    private String orderId;

    private TossPaymentCancelResponse() {
    }

    public TossPaymentCancelResponse(
        String errorCode,
        String errorMsg,
        String orderId
    ) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.orderId = orderId;
    }
}
