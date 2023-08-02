package project.swithme.order.core.web.payment.out.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.swithme.order.common.configuration.feign.BasicAuthConfiguration;
import project.swithme.order.core.web.payment.out.adapter.request.PaymentApproveRequest;
import project.swithme.order.core.web.payment.out.adapter.response.PaymentApproveResponse;

@FeignClient(
    name = "tossClient",
    url = "https://api.tosspayments.com",
    configuration = {BasicAuthConfiguration.class}
)
public interface PaymentApprovalAdapter {

    @PostMapping(
        value = "/v1/payments/{paymentKey}"
    )
    PaymentApproveResponse requestApproval(
        @PathVariable("paymentKey") String paymentKey,
        @RequestBody PaymentApproveRequest request
    );
}
