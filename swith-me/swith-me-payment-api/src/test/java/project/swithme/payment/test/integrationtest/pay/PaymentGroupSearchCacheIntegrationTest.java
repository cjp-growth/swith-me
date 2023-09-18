package project.swithme.payment.test.integrationtest.pay;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import project.swithme.payment.core.presentation.query.PaymentGroupSearchAPI;
import project.swithme.payment.core.presentation.query.PaymentTypeSearchAPI;
import project.swithme.payment.test.IntegrationTestBase;

@DisplayName("[IntegrationTest] 결제 그룹/타입 조회 캐싱 테스트")
class PaymentGroupSearchCacheIntegrationTest extends IntegrationTestBase {

    @SpyBean
    private PaymentGroupSearchAPI paymentGroupSearchAPI;

    @SpyBean
    private PaymentTypeSearchAPI paymentTypeSearchAPI;

    @BeforeEach
    void setup() {
        ServletRequestAttributes attributes =
            new ServletRequestAttributes(new MockHttpServletRequest());
        RequestContextHolder.setRequestAttributes(attributes);
    }

    @AfterEach
    void cleanup() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    @DisplayName("캐싱을 적용하면 메서드(searchPayGroup)를 여러번 호출하더라도 실제로는 한 번만 호출된다.")
    void pay_group_method_call_count_with_cache_test() {

        paymentGroupSearchAPI.searchPayGroup();
        paymentGroupSearchAPI.searchPayGroup();
        paymentGroupSearchAPI.searchPayGroup();

        verify(paymentGroupSearchAPI, times(1)).searchPayGroup();
    }

    @Test
    @DisplayName("캐싱을 적용하면 메서드(searchPayTypes)를 여러번 호출하더라도 실제로는 한 번만 호출된다.")
    void pay_types_method_call_count_with_cache_test() {
        paymentTypeSearchAPI.searchPayTypes("CASH");
        paymentTypeSearchAPI.searchPayTypes("CASH");
        paymentTypeSearchAPI.searchPayTypes("CASH");

        verify(paymentTypeSearchAPI, times(1)).searchPayTypes("CASH");
    }
}
