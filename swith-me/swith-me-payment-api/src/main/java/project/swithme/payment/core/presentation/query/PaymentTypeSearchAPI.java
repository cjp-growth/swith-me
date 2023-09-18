package project.swithme.payment.core.presentation.query;

import static project.study.support.codeandmessage.common.SuccessCodeAndMessage.OK;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.study.support.response.success.ApiResponse;
import project.swithme.payment.common.configuration.business.PaymentGroup;
import project.swithme.payment.core.presentation.query.response.PayTypesResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentTypeSearchAPI {

    private final PaymentGroup paymentGroup;

    @GetMapping("/pay-types")
    @Cacheable(cacheNames = "payTypes")
    public ApiResponse<PayTypesResponse> searchPayTypes(@RequestParam("payType") String payType) {
        PayTypesResponse data = new PayTypesResponse(paymentGroup.getPayTypes(payType));
        return ApiResponse.of(data, OK);
    }
}
