package project.swithme.order.core.web.payment.out.port;

import lombok.Getter;

@Getter
public class PaymentCancelRequest {

    private String cancelReason;

    private PaymentCancelRequest() {
    }

    public PaymentCancelRequest(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    @Override
    public String toString() {
        return cancelReason;
    }
}
