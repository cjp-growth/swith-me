package project.swithme.payment.core.presentation;

import static project.study.support.codeandmessage.common.SuccessCodeAndMessage.OK;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.study.support.response.success.ApiResponse;
import project.swithme.payment.core.application.PaymentCancelUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments/fail")
public class TossCardPaymentCancelAPI {

    private final PaymentCancelUseCase paymentCancelUseCase;

    @PostMapping
    public ApiResponse<Void> cancelPayment(
        @RequestParam("paymentKey") String paymentKey,
        @RequestParam("cancelReason") String cancelReason
    ) {
        paymentCancelUseCase.cancelPayment(paymentKey, cancelReason);
        return ApiResponse.of(OK);
    }
}
