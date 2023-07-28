package project.swithme.order.core.web.payment.presentation;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.swithme.order.common.response.ApiResponse;
import project.swithme.order.core.domain.payment.entity.Payment;
import project.swithme.order.core.domain.payment.entity.toss.PaymentType;
import project.swithme.order.core.domain.payment.entity.toss.pojo.payments.TossPayment;
import project.swithme.order.core.web.payment.facade.PaymentFacade;
import project.swithme.order.core.web.payment.presentation.response.PaymentCompleteResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class TossPaymentAPI {

    private final PaymentFacade paymentFacade;

    @GetMapping("/toss")
    public ApiResponse<PaymentCompleteResponse> pay(
        @RequestParam("orderId") String orderId,
        @RequestParam("paymentKey") String paymentKey,
        @RequestParam("paymentType") PaymentType paymentType,
        @RequestParam("amount") BigDecimal amount
    ) {
        Payment payment = new TossPayment(orderId, paymentKey, paymentType, amount);
        PaymentCompleteResponse data = new PaymentCompleteResponse(
            paymentFacade.pay(orderId, payment)
        );
        return ApiResponse.of(data, HttpStatus.CREATED);
    }
}
