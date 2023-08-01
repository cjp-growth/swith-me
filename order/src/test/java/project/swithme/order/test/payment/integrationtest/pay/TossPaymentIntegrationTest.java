package project.swithme.order.test.payment.integrationtest.pay;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static project.swithme.order.common.testhelper.env.TossPaymentFixture.getPaymentKey;
import static project.swithme.order.common.testhelper.order.fixture.OrderFixture.createOrder;
import static project.swithme.order.common.testhelper.payment.fixture.PaymentFixture.FIXED_TOTAL_PRICE;
import static project.swithme.order.common.testhelper.payment.fixture.TossFixture.createApprovedCommand;
import static project.swithme.order.core.domain.order.entity.OrderStatus.PAYMENT_REQUEST;
import static project.swithme.order.core.domain.payment.entity.PaymentType.NORMAL;
import com.fasterxml.uuid.Generators;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import project.swithme.order.common.annotation.Description;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.web.order.application.OrderQueryUseCase;
import project.swithme.order.core.web.order.exception.OrderNotFoundException;
import project.swithme.order.core.web.payment.application.PaymentSaveUseCase;
import project.swithme.order.core.web.payment.facade.PaymentFacade;
import project.swithme.order.core.web.payment.facade.validator.PaymentValidator;
import project.swithme.order.core.web.payment.out.port.PaymentPort;
import project.swithme.order.test.IntegrationTestBase;

@Description(content = "토스 API 서비스를 호출하기 때문에 필요한 일부 모킹 처리 ")
@DisplayName("[IntegrationTest] 토스 결제 통합 테스트")
class TossPaymentIntegrationTest extends IntegrationTestBase {

    private PaymentFacade paymentFacade;

    @SpyBean
    private OrderQueryUseCase orderQueryUseCase;

    @SpyBean
    private PaymentValidator paymentValidator;

    @SpyBean
    private PaymentSaveUseCase paymentSaveUseCase;

    @MockBean
    private PaymentPort paymentPort;

    @BeforeEach
    void setUp() {
        paymentFacade = new PaymentFacade(
            orderQueryUseCase,
            paymentValidator,
            paymentSaveUseCase,
            paymentPort
        );
    }

    @Test
    @DisplayName("결제가 승인되면 PK를 반환한다.")
    void payment_success_test() {
        Order order = persistenceHelper.persist(createOrder(PAYMENT_REQUEST));

        when(paymentPort.requestApproval(
            getPaymentKey(),
            order.getUniqueId().toString(),
            FIXED_TOTAL_PRICE
        )).thenReturn(createApprovedCommand());

        Long paymentId = paymentFacade.pay(
            order.getUniqueId().toString(),
            getPaymentKey(),
            NORMAL,
            FIXED_TOTAL_PRICE
        );

        assertNotNull(paymentId);
    }

    @Test
    @DisplayName("주문이 존재하지 않는데 결제를 요청하면 OrderNotFoundException이 발생한다.")
    void toss_payment_failure_by_invalid_order_test() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        BigDecimal price = new BigDecimal(130_000L);

        assertThatThrownBy(
            () -> paymentFacade.pay(uuid.toString(), getPaymentKey(), NORMAL, price))
            .isExactlyInstanceOf(OrderNotFoundException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("주문을 찾을 수 없습니다.");
    }
}
