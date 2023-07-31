package project.swithme.order.core.web.payment.out.adapter.request;

import lombok.Getter;

@Getter
public class TossPaymentApproveRequest {

    private String orderId;
    private Long amount;

    private TossPaymentApproveRequest() {
    }

    public TossPaymentApproveRequest(
        String orderId,
        Long amount
    ) {
        this.orderId = orderId;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("orderId: %s, amount: %s", orderId, amount);
    }
}
