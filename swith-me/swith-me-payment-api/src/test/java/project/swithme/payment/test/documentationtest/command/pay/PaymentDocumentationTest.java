package project.swithme.payment.test.documentationtest.command.pay;

import static order.OrderFixture.createOrder;
import static order.PaymentFixture.FIXED_TOTAL_PRICE;
import static order.PaymentFixture.FIXED_TOTAL_PRICE_PARAM;
import static order.PaymentFixture.getPaymentKey;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.swithme.domain.core.order.entity.OrderStatus.PAYMENT_REQUEST;
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
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.payment.entity.PaymentType;
import project.swithme.payment.common.persistence.PersistenceHelper;
import project.swithme.payment.core.facade.PaymentFacade;
import project.swithme.payment.core.presentation.TossCardPaymentAPI;
import project.swithme.payment.test.IntegrationTestBase;

@AutoConfigureMockMvc
@DisplayName("[DocumentationTest] 주문 생성 API 테스트")
class PaymentDocumentationTest extends IntegrationTestBase {

    private final String apiUrl = "/api/payments/toss?paymentType={paymentType}&orderId={orderId}&paymentKey={paymentKey}&amount={amount}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersistenceHelper persistenceHelper;

    @InjectMocks
    private TossCardPaymentAPI paymentAPI;

    @Mock
    private PaymentFacade paymentFacade;

    @BeforeEach
    void setUp() {
        this.paymentAPI = new TossCardPaymentAPI(paymentFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentAPI)
            .build();
    }

    @Test
    @DisplayName("주문이 성공하면 201 CREATED가 반환된다.")
    void order_create_test() throws Exception {
        Order newOrder = persistenceHelper.persist(createOrder(PAYMENT_REQUEST));
        String orderId = newOrder.getUniqueId().toString();

        when(paymentFacade.requestApproval(
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
