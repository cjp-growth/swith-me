package project.swithme.payment.core.out.client.toss.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.swithme.payment.common.configuration.feign.BasicAuthConfiguration;
import project.swithme.payment.core.out.client.toss.adapter.request.PaymentApproveRequest;
import project.swithme.payment.core.out.client.toss.adapter.response.success.PaymentApproveResponse;

@FeignClient(
    name = "tossClient",
    url = "https://api.tosspayments.com",
    configuration = {BasicAuthConfiguration.class}
)
public interface TossPaymentAdapter {

    @PostMapping(
        value = "/v1/payments/{paymentKey}"
    )
    PaymentApproveResponse requestApproval(
        @PathVariable("paymentKey") String paymentKey,
        @RequestBody PaymentApproveRequest request
    );
}
