package project.swithme.payment.core.out.port.adapter.request;

import lombok.Getter;

@Getter
public class PaymentApproveRequest {

    private String orderId;
    private Long amount;

    private PaymentApproveRequest() {
    }

    public PaymentApproveRequest(
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
