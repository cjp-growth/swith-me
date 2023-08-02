package project.swithme.order.core.web.payment.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.swithme.order.core.web.payment.application.PaymentCancelUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments/fail")
public class PaymentCancelAPI {

    private final PaymentCancelUseCase paymentCancelUseCase;

    @PostMapping
    public ResponseEntity<Void> cancelPayment(
        @RequestParam("paymentKey") String paymentKey,
        @RequestParam("cancelReason") String cancelReason
    ) {
        paymentCancelUseCase.cancelPayment(paymentKey, cancelReason);
        return ResponseEntity.ok()
            .build();
    }
}
