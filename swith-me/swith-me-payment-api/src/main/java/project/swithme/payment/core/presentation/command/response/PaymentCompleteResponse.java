package project.swithme.payment.core.presentation.command.response;

import lombok.Getter;

@Getter
public class PaymentCompleteResponse {

    private final Long paymentId;

    public PaymentCompleteResponse(Long paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return paymentId.toString();
    }
}
