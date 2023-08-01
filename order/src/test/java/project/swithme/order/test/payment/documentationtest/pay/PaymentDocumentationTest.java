package project.swithme.order.test.payment.documentationtest.pay;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.swithme.order.common.testhelper.env.TossPaymentFixture.getPaymentKey;
import static project.swithme.order.common.testhelper.order.fixture.OrderFixture.createOrder;
import static project.swithme.order.common.testhelper.payment.fixture.PaymentFixture.FIXED_TOTAL_PRICE;
import static project.swithme.order.common.testhelper.payment.fixture.PaymentFixture.FIXED_TOTAL_PRICE_PARAM;
import static project.swithme.order.core.domain.order.entity.OrderStatus.PAYMENT_REQUEST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.payment.entity.PaymentType;
import project.swithme.order.core.web.payment.facade.PaymentFacade;
import project.swithme.order.core.web.payment.presentation.TossPaymentAPI;
import project.swithme.order.test.IntegrationTestBase;

@AutoConfigureMockMvc
@DisplayName("[DocumentationTest] 주문 생성 API 테스트")
class PaymentDocumentationTest extends IntegrationTestBase {

    private final String apiUrl = "/api/payments/toss?paymentType={paymentType}&orderId={orderId}&paymentKey={paymentKey}&amount={amount}";

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TossPaymentAPI tossPaymentAPI;

    @Mock
    private PaymentFacade paymentFacade;

    @BeforeEach
    void setUp() {
        this.tossPaymentAPI = new TossPaymentAPI(paymentFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(tossPaymentAPI)
            .build();
    }

    @Test
    @DisplayName("주문이 성공하면 201 CREATED가 반환된다.")
    void order_create_test() throws Exception {
        Order newOrder = persistenceHelper.persist(createOrder(PAYMENT_REQUEST));
        String orderId = newOrder.getUniqueId().toString();

        when(paymentFacade.pay(
            orderId,
            getPaymentKey(),
            PaymentType.NORMAL,
            FIXED_TOTAL_PRICE
        )).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/payments/toss")
                .content(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("orderId", orderId)
                .queryParam("paymentKey", getPaymentKey())
                .queryParam("paymentType", PaymentType.NORMAL.name())
                .queryParam("amount", FIXED_TOTAL_PRICE_PARAM)
            )
            .andExpect(status().isCreated());
    }
}
