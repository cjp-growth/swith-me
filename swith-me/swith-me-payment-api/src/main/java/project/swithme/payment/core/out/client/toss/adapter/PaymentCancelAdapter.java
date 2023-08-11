package project.swithme.payment.core.out.client.toss.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.swithme.payment.common.configuration.feign.BasicAuthConfiguration;
import project.swithme.payment.core.out.client.toss.adapter.request.PaymentCancelRequest;
import project.swithme.payment.core.out.client.toss.adapter.response.success.CancelsResponse;

@FeignClient(
    name = "paymentCancelPort",
    url = "${payment.toss.baseUrl}",
    configuration = {BasicAuthConfiguration.class}
)
public interface PaymentCancelAdapter {

    @PostMapping(value = "${payment.toss.path}/{paymentKey}/cancel")
    CancelsResponse requestCancel(
        @PathVariable("paymentKey") String paymentKey,
        @RequestBody PaymentCancelRequest data
    );
}
