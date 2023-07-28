package project.swithme.order.core.web.payment.presentation.response;

public class PaymentCompleteResponse {

    private final Long paymentId;

    public PaymentCompleteResponse(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    @Override
    public String toString() {
        return paymentId.toString();
    }
}