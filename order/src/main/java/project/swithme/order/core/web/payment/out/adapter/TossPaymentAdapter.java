package project.swithme.order.core.web.payment.out.adapter;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import project.swithme.order.core.domain.payment.entity.command.TossPaymentCommand;
import project.swithme.order.core.web.payment.exception.PaymentFailureException;
import project.swithme.order.core.web.payment.out.adapter.extractor.PaymentInfoExtractor;
import project.swithme.order.core.web.payment.out.adapter.request.TossPaymentApproveRequest;
import project.swithme.order.core.web.payment.out.adapter.response.TossPaymentApproveResponse;
import project.swithme.order.core.web.payment.out.port.PaymentPort;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossPaymentAdapter implements PaymentPort {

    private final PaymentApprovalAdapter paymentApprovalAdapter;
    private final PaymentInfoExtractor paymentInfoExtractor;

    @Override
    public TossPaymentCommand requestApproval(
        String paymentKey,
        String orderId,
        BigDecimal amount
    ) {
        TossPaymentApproveResponse response;
        try {
            response = paymentApprovalAdapter.requestApproval(
                paymentKey,
                new TossPaymentApproveRequest(orderId, amount.longValue())
            );
            return convertToDomainLanguage(response);
        } catch (RestClientException e) {
            // TODO. 에러 처리 조금 더 고민
            log.error("");
            throw new RuntimeException();
        }
    }

    private TossPaymentCommand convertToDomainLanguage(TossPaymentApproveResponse response) {
        if (response == null) {
            throw new PaymentFailureException();
        }
        return paymentInfoExtractor.extractInfo(response);
    }
}
