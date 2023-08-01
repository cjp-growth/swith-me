package project.swithme.order.core.web.payment.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.swithme.order.common.response.ApiResponse;
import project.swithme.order.core.web.payment.application.PaymentCancelUseCase;
import project.swithme.order.core.web.payment.out.adapter.response.CancelsResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments/fail")
public class TossPaymentCancel {

    private final PaymentCancelUseCase paymentCancelUseCase;

    @PostMapping
    public ApiResponse<CancelsResponse> test(
        @RequestParam("paymentKey") String paymentKey,
        @RequestParam("cancelReason") String cancelReason
    ) {
        CancelsResponse data = paymentCancelUseCase.cancel(paymentKey, cancelReason);
        return ApiResponse.of(data, HttpStatus.BAD_REQUEST);
    }
}
