package project.swithme.order.test.order.integrationtest.save;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.order.core.facade.OrderFacade;
import project.swithme.order.core.presentation.command.request.OrderCreateRequest;
import project.swithme.order.test.IntegrationTestBase;

@DisplayName("[IntegrationTest] 주문 통합 테스트")
class OrderIntegrationTest extends IntegrationTestBase {

    @Autowired
    private OrderFacade orderFacade;

    @Test
    @DisplayName("상품 주문이 성공하면 PK가 Null이 아니다.")
    void order_create_test() {
        StudyWithMeUser studyWithMeUser = new StudyWithMeUser(1L);
        OrderCreateRequest orderCreateRequest = createOrderRequest();

        Long newOrderId = orderFacade.createOrder(studyWithMeUser, orderCreateRequest.toCommand());
        assertNotNull(newOrderId);
    }

    private OrderCreateRequest createOrderRequest() {
        return new OrderCreateRequest(
            1L,
            1L,
            BigDecimal.valueOf(75000),
            1L,
            BigDecimal.valueOf(10000),
            "스터디 카페 1달 정기 이용권",
            "KAKAO_PAY"
        );
    }
}
