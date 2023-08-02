package project.swithme.order.core.web.payment.application;

public interface PaymentCancelUseCase {

    void cancelPayment(String paymentKey, String cancelReason);
}
