package project.swithme.order.core.web.payment.application.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.swithme.order.common.exception.error.CodeAndMessage;
import project.swithme.order.core.web.payment.application.PaymentCancelUseCase;
import project.swithme.order.core.web.payment.out.port.PaymentCancelPort;
import project.swithme.order.core.web.payment.out.port.PaymentCancelRequest;

@Service
@RequiredArgsConstructor
public class PaymentCancelService implements PaymentCancelUseCase {

    private final PaymentCancelPort paymentCancelPort;
    private final CodeAndMessageParser messageParser;

    @Override
    @Transactional
    public void cancelPayment(
        String paymentKey,
        String cancelReason
    ) {
        try {
            paymentCancelPort.requestCancel(
                paymentKey,
                new PaymentCancelRequest(cancelReason)
            );
        } catch (FeignException e) {
            CodeAndMessage codeAndMessage = messageParser.parse(e.getMessage());
            throw new TossPaymentException(codeAndMessage);
        }
    }
}
