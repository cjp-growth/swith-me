package project.swithme.order.test.payment.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static project.swithme.order.common.testhelper.payment.fixture.TossFixture.createToss;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[UnitTest] 토스 결제 단위 테스트")
class TossPaymentUnitTest {

    @Test
    @DisplayName("Toss 객체를 생성할 수 있다.")
    void toss_create_test() {
        assertNotNull(createToss(1L));
    }
}
