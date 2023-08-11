package project.swithme.payment.test.documentationtest.command.cancel;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.swithme.payment.core.application.PaymentCancelUseCase;
import project.swithme.payment.core.presentation.TossCardPaymentCancelAPI;
import project.swithme.payment.test.IntegrationTestBase;

@AutoConfigureMockMvc
@DisplayName("[DocumentationTest] 주문 취소 API 테스트")
class PaymentCancelDocumentationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TossCardPaymentCancelAPI paymentCancelAPI;

    @Mock
    private PaymentCancelUseCase paymentCancelUseCase;

    @BeforeEach
    void setUp() {
        this.paymentCancelAPI = new TossCardPaymentCancelAPI(paymentCancelUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentCancelAPI)
            .build();
    }

    //@Test
    @DisplayName("주문 취소가 성공하면 200 OK가 반환된다.")
    void order_create_test() throws Exception {
        String cancelReason = "단순 변심";

        mockMvc.perform(MockMvcRequestBuilders
                    .post("/api/payments/fail")
                    .content(APPLICATION_JSON_VALUE)
//                .queryParam("paymentKey", getPaymentKey())
                    .queryParam("cancelReason", cancelReason)
            )
            .andExpect(status().isOk());
    }
}
