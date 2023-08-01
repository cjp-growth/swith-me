package project.swithme.order.core.web.payment.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.swithme.order.core.web.payment.application.PaymentCancelUseCase;
import project.swithme.order.core.web.payment.out.adapter.response.CancelsResponse;
import project.swithme.order.core.web.payment.out.port.PaymentCancelPort;
import project.swithme.order.core.web.payment.out.port.TossPaymentCancelRequest;

@Service
@RequiredArgsConstructor
public class PaymentCancelService implements PaymentCancelUseCase {

    private final PaymentCancelPort paymentCancelPort;

    @Override
    @Transactional
    public CancelsResponse cancel(
        String paymentKey,
        String cancelReason
    ) {
        return paymentCancelPort.requestCancel(
            paymentKey,
            new TossPaymentCancelRequest(cancelReason)
        );
    }
}
