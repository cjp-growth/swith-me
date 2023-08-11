package project.swithme.payment.core.application.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study.support.codeandmessage.CodeAndMessage;
import project.swithme.payment.common.codeandmessage.parser.TossCodeAndMessageParser;
import project.swithme.payment.core.application.PaymentCancelUseCase;
import project.swithme.payment.core.exception.TossPaymentException;
import project.swithme.payment.core.out.port.PaymentCancelPort;
import project.swithme.payment.core.out.port.adapter.request.PaymentCancelRequest;

@Service
@RequiredArgsConstructor
public class PaymentCancelService implements PaymentCancelUseCase {

    private final PaymentCancelPort paymentCancelPort;
    private final TossCodeAndMessageParser messageParser;

    @Override
    @Transactional
    public void cancelPayment(
        String paymentKey,
        String cancelReason
    ) {
        try {
            paymentCancelPort.requestCancel(paymentKey, new PaymentCancelRequest(cancelReason));
        } catch (FeignException exception) {
            CodeAndMessage codeAndMessage = messageParser.parsePaymentCancelFailureMessage(
                exception.getMessage());
            throw new TossPaymentException(codeAndMessage, exception.getMessage());
        }
    }
}
