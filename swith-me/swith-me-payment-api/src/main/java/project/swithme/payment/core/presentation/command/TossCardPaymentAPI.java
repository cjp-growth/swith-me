package project.swithme.payment.core.presentation.command;

import static project.study.support.codeandmessage.common.SuccessCodeAndMessage.CREATED;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.study.support.response.success.ApiResponse;
import project.swithme.domain.core.payment.entity.PaymentType;
import project.swithme.payment.core.facade.PaymentFacade;
import project.swithme.payment.core.presentation.command.response.PaymentCompleteResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class TossCardPaymentAPI {

    private final PaymentFacade paymentFacade;

    @GetMapping("/toss")
    public ApiResponse<PaymentCompleteResponse> pay(
        @RequestParam("orderId") String orderId,
        @RequestParam("paymentKey") String paymentKey,
        @RequestParam("paymentType") PaymentType paymentType,
        @RequestParam("amount") BigDecimal amount
    ) {
        Long paymentId = paymentFacade.requestApproval(orderId, paymentKey, paymentType, amount);
        PaymentCompleteResponse data = new PaymentCompleteResponse(paymentId);
        return ApiResponse.of(data, CREATED);
    }
}
