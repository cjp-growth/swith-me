package project.swithme.order.core.web.payment.out.port;

import lombok.Getter;

@Getter
public class TossPaymentCancelRequest {

    private String cancelReason;

    private TossPaymentCancelRequest() {
    }

    public TossPaymentCancelRequest(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    @Override
    public String toString() {
        return cancelReason;
    }
}
