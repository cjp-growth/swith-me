package project.swithme.payment.core.out.client.toss;

import static project.study.support.codeandmessage.common.CommonErrorCodeAndMessage.API_SPEC_UN_MATCHED;
import static project.study.support.codeandmessage.common.CommonErrorCodeAndMessage.BAD_GATEWAY;
import feign.FeignException;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import project.study.support.codeandmessage.CodeAndMessage;
import project.study.support.codeandmessage.parser.CodeAndMessageParser;
import project.swithme.payment.core.exception.PaymentFailureException;
import project.swithme.payment.core.exception.TossPaymentException;
import project.swithme.payment.core.facade.command.PaymentCommand;
import project.swithme.payment.core.out.PaymentPort;
import project.swithme.payment.core.out.client.toss.adapter.TossPaymentAdapter;
import project.swithme.payment.core.out.client.toss.adapter.extractor.PaymentInfoExtractor;
import project.swithme.payment.core.out.client.toss.adapter.request.PaymentApproveRequest;
import project.swithme.payment.core.out.client.toss.adapter.response.success.PaymentApproveResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentClient implements PaymentPort {

    private final TossPaymentAdapter tossPaymentAdapter;
    private final PaymentInfoExtractor paymentInfoExtractor;
    private final CodeAndMessageParser codeAndMessageParser;

    @Override
    public PaymentCommand requestApproval(
        String paymentKey,
        String orderId,
        BigDecimal amount
    ) {
        PaymentApproveResponse response;
        try {
            response = tossPaymentAdapter.requestApproval(
                paymentKey,
                new PaymentApproveRequest(orderId, amount.longValue())
            );
            return convertToDomainLanguage(response);
        } catch (FeignException exception) {
            CodeAndMessage codeAndMessage = parseMessage(exception);
            throw new TossPaymentException(codeAndMessage);
        } catch (IllegalArgumentException | PaymentFailureException exception) {
            throw new PaymentFailureException(API_SPEC_UN_MATCHED, exception.getMessage());
        } catch (Exception exception) {
            throw new PaymentFailureException(BAD_GATEWAY, exception.getMessage());
        }
    }

    private PaymentCommand convertToDomainLanguage(PaymentApproveResponse response) {
        return paymentInfoExtractor.extractInfo(response);
    }

    private CodeAndMessage parseMessage(FeignException exception) {
        return codeAndMessageParser.parseRequestApprovalFailureMessage(exception.getMessage());
    }
}
