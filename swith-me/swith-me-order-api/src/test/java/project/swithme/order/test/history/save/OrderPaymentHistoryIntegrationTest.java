package project.swithme.order.test.history.save;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.domain.core.history.entity.OrderPaymentHistory;
import project.swithme.domain.core.history.repository.OrderPaymentHistoryJpaRepository;
import project.swithme.order.core.facade.OrderFacade;
import project.swithme.order.core.presentation.request.OrderCreateRequest;
import project.swithme.order.test.IntegrationTestBase;

@DisplayName("[IntegrationTest] 주문/결제 내역 통합 테스트")
class OrderPaymentHistoryIntegrationTest extends IntegrationTestBase {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private OrderPaymentHistoryJpaRepository historyJpaRepository;

    @Test
    @DisplayName("변경사항(생성/수정/삭제)가 발생하면 히스토리가 남는다.")
    void history_create_test() {
        StudyWithMeUser studyWithMeUser = new StudyWithMeUser(1L);
        OrderCreateRequest orderCreateRequest = getOrderCreateRequest();

        orderFacade.createOrder(studyWithMeUser, orderCreateRequest.toCommand());
        OrderPaymentHistory history = historyJpaRepository.findById(1L).orElseThrow();

        assertNotNull(history);
    }

    private OrderCreateRequest getOrderCreateRequest() {
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
