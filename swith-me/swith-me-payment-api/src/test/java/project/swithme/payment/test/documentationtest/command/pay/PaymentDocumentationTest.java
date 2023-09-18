package project.swithme.payment.test.documentationtest.command.pay;

import static order.OrderFixture.createOrder;
import static order.PaymentFixture.FIXED_TOTAL_PRICE;
import static order.PaymentFixture.FIXED_TOTAL_PRICE_PARAM;
import static order.PaymentFixture.getPaymentKey;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.swithme.domain.core.order.entity.OrderStatus.PAYMENT_REQUEST;
import static project.swithme.payment.test.documentationtest.snippet.PaymentSnippet.TOSS_CARD_PAYMENT_HANDLER;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.payment.entity.PaymentType;
import project.swithme.payment.common.persistence.PersistenceHelper;
import project.swithme.payment.core.facade.PaymentFacade;
import project.swithme.payment.test.DocumentationTestBase;

@DisplayName("[DocumentationTest] 주문 생성 API 테스트")
class PaymentDocumentationTest extends DocumentationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersistenceHelper persistenceHelper;

    @MockBean
    private PaymentFacade paymentFacade;

    @Test
    @DisplayName("토스 카드 결제가 완료 되면 201 CREATED가 반환된다.")
    void toss_payment_pay_test() throws Exception {
        Order newOrder = persistenceHelper.persist(createOrder(PAYMENT_REQUEST));
        UUID orderId = newOrder.getUniqueId();

        when(paymentFacade.requestApproval(
            orderId.toString(),
            getPaymentKey(),
            PaymentType.NORMAL,
            FIXED_TOTAL_PRICE
        )).thenReturn(newOrder.getId());

        mockMvc.perform(RestDocumentationRequestBuilders
                .get("/api/payments/toss")
                .queryParam("orderId", orderId.toString())
                .queryParam("paymentKey", getPaymentKey())
                .queryParam("paymentType", PaymentType.NORMAL.name())
                .queryParam("amount", FIXED_TOTAL_PRICE_PARAM)
            )
            .andDo(TOSS_CARD_PAYMENT_HANDLER)
            .andExpect(status().isCreated());
    }
}
