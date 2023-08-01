package project.swithme.order.core.web.payment.application;

import project.swithme.order.core.web.payment.out.adapter.response.CancelsResponse;

public interface PaymentCancelUseCase {

    CancelsResponse cancel(String paymentKey, String cancelReason);
}
