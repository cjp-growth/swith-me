package project.swithme.order.core.web.order.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.swithme.order.common.configuration.business.PaymentGroup;
import project.swithme.order.common.response.ApiResponse;
import project.swithme.order.core.web.order.presentation.response.PayGroupResponse;
import project.swithme.order.core.web.order.presentation.response.PayTypeResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class PaymentGroupSearchAPI {

    private final PaymentGroup paymentGroup;

    @GetMapping("/pay-group")
    @Cacheable(cacheNames = "payGroup")
    public ApiResponse<PayGroupResponse> searchPayGroup() {
        PayGroupResponse data = new PayGroupResponse(paymentGroup.getPayGroups());
        return ApiResponse.of(data, HttpStatus.OK);
    }

    @GetMapping
    @Cacheable(cacheNames = "payTypes")
    public ApiResponse<PayTypeResponse> searchPayTypes(@RequestParam("pay-group") String payGroup) {
        PayTypeResponse data = new PayTypeResponse(paymentGroup.getPayTypes(payGroup));
        return ApiResponse.of(data, HttpStatus.OK);
    }
}
