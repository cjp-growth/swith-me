package project.swithme.payment.core.application.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study.support.response.codeandmessage.CodeAndMessage;
import project.swithme.payment.core.application.PaymentCancelUseCase;
import project.swithme.payment.core.out.port.PaymentCancelPort;
import project.swithme.payment.core.out.port.request.PaymentCancelRequest;

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
