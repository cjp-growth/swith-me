package project.swithme.payment.core.out.client.toss.adapter.request;

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
