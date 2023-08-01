package project.swithme.order.core.web.payment.out.port;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.swithme.order.common.configuration.feign.BasicAuthConfiguration;
import project.swithme.order.core.web.payment.out.adapter.response.CancelsResponse;

@FeignClient(
    name = "tossCancelClient",
    url = "https://api.tosspayments.com",
    configuration = {BasicAuthConfiguration.class}
)
public interface PaymentCancelPort {

    @PostMapping(
        value = "/v1/payments/{paymentKey}/cancel"
    )
    CancelsResponse requestCancel(
        @PathVariable("paymentKey") String paymentKey,
        @RequestBody TossPaymentCancelRequest data
    );
}
