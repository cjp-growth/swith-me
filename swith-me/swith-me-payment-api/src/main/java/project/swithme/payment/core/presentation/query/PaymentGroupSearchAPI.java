package project.swithme.payment.core.presentation.query;

import static project.study.support.codeandmessage.common.SuccessCodeAndMessage.OK;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.study.support.response.success.ApiResponse;
import project.swithme.payment.common.configuration.business.PaymentGroup;
import project.swithme.payment.core.presentation.query.response.PayGroupsResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentGroupSearchAPI {

    private final PaymentGroup paymentGroup;

    @GetMapping("/pay-group")
    @Cacheable(cacheNames = "payGroup")
    public ApiResponse<PayGroupsResponse> searchPayGroup() {
        PayGroupsResponse data = new PayGroupsResponse(paymentGroup.getPayGroups());
        return ApiResponse.of(data, OK);
    }
}
