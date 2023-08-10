package project.swithme.payment.core.application;

public interface PaymentCancelUseCase {

    void cancelPayment(String paymentKey, String cancelReason);
}
