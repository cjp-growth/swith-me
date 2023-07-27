package project.swithme.order.test.order.integrationtest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import project.swithme.order.core.web.order.presentation.PaymentGroupSearchAPI;
import project.swithme.order.test.IntegrationTestBase;

@DisplayName("[IntegrationTest] 결제 그룹/타입 조회 캐싱 테스트")
class PaymentGroupSearchIntegrationTest extends IntegrationTestBase {

    @SpyBean
    private PaymentGroupSearchAPI paymentGroupSearchAPI;

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
        paymentGroupSearchAPI.searchPayTypes("CASH");
        paymentGroupSearchAPI.searchPayTypes("CASH");
        paymentGroupSearchAPI.searchPayTypes("CASH");

        verify(paymentGroupSearchAPI, times(1)).searchPayTypes("CASH");
    }
}
