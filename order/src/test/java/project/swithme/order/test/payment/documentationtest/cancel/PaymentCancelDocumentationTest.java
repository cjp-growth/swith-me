package project.swithme.order.test.payment.documentationtest.cancel;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.swithme.order.common.testhelper.payment.fixture.PaymentFixture.getPaymentKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.swithme.order.core.web.payment.application.PaymentCancelUseCase;
import project.swithme.order.core.web.payment.presentation.PaymentCancelAPI;
import project.swithme.order.test.IntegrationTestBase;

@AutoConfigureMockMvc
@DisplayName("[DocumentationTest] 주문 취소 API 테스트")
class PaymentCancelDocumentationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PaymentCancelAPI paymentCancelAPI;

    @Mock
    private PaymentCancelUseCase paymentCancelUseCase;

    @BeforeEach
    void setUp() {
        this.paymentCancelAPI = new PaymentCancelAPI(paymentCancelUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentCancelAPI)
            .build();
    }

    @Test
    @DisplayName("주문 취소가 성공하면 200 OK가 반환된다.")
    void order_create_test() throws Exception {
        String cancelReason = "단순 변심";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/payments/fail")
                .content(APPLICATION_JSON_VALUE)
                .queryParam("paymentKey", getPaymentKey())
                .queryParam("cancelReason", cancelReason)
            )
            .andExpect(status().isOk());
    }
}
