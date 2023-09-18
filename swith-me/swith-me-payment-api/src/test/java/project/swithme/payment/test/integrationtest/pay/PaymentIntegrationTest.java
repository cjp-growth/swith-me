package project.swithme.payment.test.integrationtest.pay;

import static order.OrderFixture.createOrder;
import static order.PaymentFixture.FIXED_TOTAL_PRICE;
import static order.PaymentFixture.getOrderUniqueId;
import static order.PaymentFixture.getPaymentKey;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static project.swithme.domain.core.order.entity.OrderStatus.PAYMENT_REQUEST;
import static project.swithme.domain.core.payment.entity.PaymentType.NORMAL;
import static project.swithme.payment.common.mockresponse.OrderCommandMockResponse.createApprovedCommand;
import static project.swithme.payment.common.mockresponse.OrderCommandMockResponse.createNotApprovedCommand;
import static project.swithme.payment.common.mockresponse.OrderCommandMockResponse.getCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import project.swithme.domain.core.order.event.PaymentSuccessEvent;
import project.swithme.payment.common.command.OrderValidationCommand;
import project.swithme.payment.common.persistence.PersistenceHelper;
import project.swithme.payment.core.application.PaymentSaveUseCase;
import project.swithme.payment.core.exception.PaymentFailureException;
import project.swithme.payment.core.facade.PaymentFacade;
import project.swithme.payment.core.facade.validator.PaymentValidator;
import project.swithme.payment.core.out.EventPublisher;
import project.swithme.payment.core.out.OrderQueryPort;
import project.swithme.payment.core.out.PaymentPort;
import project.swithme.payment.test.IntegrationTestBase;

@DisplayName("[IntegrationTest] 토스 결제 통합 테스트")
class PaymentPaymentIntegrationTest extends IntegrationTestBase {

    private PaymentFacade paymentFacade;

    @SpyBean
    private OrderQueryPort orderQueryPort;

    @Autowired
    private PersistenceHelper persistenceHelper;

    @SpyBean
    private PaymentValidator paymentValidator;

    @SpyBean
    private PaymentSaveUseCase paymentSaveUseCase;

    @SpyBean
    private PaymentPort paymentPort;

    @MockBean
    private EventPublisher<PaymentSuccessEvent> eventEventPublisher;

    @BeforeEach
    void setUp() {
        paymentFacade = new PaymentFacade(
            orderQueryPort,
            paymentValidator,
            paymentSaveUseCase,
            paymentPort,
            eventEventPublisher
        );
    }

    @Test
    @DisplayName("결제가 승인되면 PK를 반환한다.")
    void payment_success_test() {
        persistenceHelper.persist(createOrder(PAYMENT_REQUEST));
        OrderValidationCommand command = getCommand();

        doReturn(command)
            .when(orderQueryPort)
            .findOrderByUniqueId(getOrderUniqueId());

        doReturn(createApprovedCommand())
            .when(paymentPort)
            .requestApproval(
                getPaymentKey(),
                getOrderUniqueId(),
                FIXED_TOTAL_PRICE
            );

        Long paymentId = paymentFacade.requestApproval(
            getOrderUniqueId(),
            getPaymentKey(),
            NORMAL,
            FIXED_TOTAL_PRICE
        );

        assertNotNull(paymentId);
    }

    @Test
    @DisplayName("주문이 존재하지 않는데 결제를 요청하면 OrderNotFoundException이 발생한다.")
    void payment_failure_by_invalid_order_test() {
        OrderValidationCommand command = getCommand();

        doReturn(command)
            .when(orderQueryPort)
            .findOrderByUniqueId(getOrderUniqueId());

        doReturn(createNotApprovedCommand())
            .when(paymentPort)
            .requestApproval(
                getPaymentKey(),
                getOrderUniqueId(),
                FIXED_TOTAL_PRICE
            );

        assertThatThrownBy(
            () -> paymentFacade.requestApproval(
                command.getOrderUniqueId().toString(),
                getPaymentKey(),
                NORMAL,
                command.getPrice()
            )
        )
            .isExactlyInstanceOf(PaymentFailureException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("결제가 정상적으로 이루어지지 않았습니다.");
    }
}
