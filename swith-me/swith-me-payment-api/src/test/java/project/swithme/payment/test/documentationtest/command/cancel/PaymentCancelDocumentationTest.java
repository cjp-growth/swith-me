package project.swithme.payment.test.documentationtest.command.cancel;

import static order.PaymentFixture.getPaymentKey;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.swithme.payment.test.documentationtest.snippet.PaymentSnippet.getOrderCancelSnippet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import project.swithme.payment.core.application.PaymentCancelUseCase;
import project.swithme.payment.test.IntegrationTestBase;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@DisplayName("[DocumentationTest] 주문 취소 API 테스트")
class PaymentCancelDocumentationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentCancelUseCase paymentCancelUseCase;

    @Test
    @DisplayName("결제 취소가 성공하면 200 OK가 반환된다.")
    void payment_cancel_test() throws Exception {
        String cancelReason = "단순 변심";

        doNothing()
            .when(paymentCancelUseCase)
            .cancelPayment(getPaymentKey(), cancelReason);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/payments/fail")
                .content(APPLICATION_JSON_VALUE)
                .queryParam("paymentKey", getPaymentKey())
                .queryParam("cancelReason", cancelReason)
            )
            .andExpect(status().isOk())
            .andDo(getOrderCancelSnippet());
    }
}
