package project.swithme.order.test.order.documentationtest.command.save;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.swithme.order.test.order.documentationtest.snippet.OrderSnippet.ORDER_CREATE_REQUEST_HANDLER;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.order.core.facade.OrderFacade;
import project.swithme.order.core.presentation.command.request.OrderCreateRequest;
import project.swithme.order.test.DocumentationTestBase;

@DisplayName("[DocumentationTest] 주문 생성 API 테스트")
class OrderCreateDocumentationTest extends DocumentationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderFacade orderFacade;

    @Test
    @DisplayName("주문이 성공하면 201 CREATED가 반환된다.")
    void order_create_test() throws Exception {
        OrderCreateRequest request = new OrderCreateRequest(
            1L,
            1L,
            BigDecimal.valueOf(100_000),
            1L,
            BigDecimal.valueOf(30_000),
            "스터디 카페 1달 정기 이용권",
            "TOSS"
        );

        StudyWithMeUser user = new StudyWithMeUser(1L);

        when(orderFacade.createOrder(
            user, request.toCommand()
        )).thenReturn(1L);

        mockMvc.perform(RestDocumentationRequestBuilders
                .post("/api/orders")
                .content(objectMapper.writeValueAsString(request))
                .contentType(APPLICATION_JSON_VALUE)
            )
            .andExpect(status().isCreated())
            .andDo(ORDER_CREATE_REQUEST_HANDLER);
    }
}
