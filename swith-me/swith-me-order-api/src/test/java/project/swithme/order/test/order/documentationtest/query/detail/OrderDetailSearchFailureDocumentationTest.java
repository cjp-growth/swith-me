package project.swithme.order.test.order.documentationtest.query.detail;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static project.swithme.order.test.order.documentationtest.snippet.OrderSnippet.ORDER_DETAIL_SEARCH_FAILURE_HANDLER;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.order.test.DocumentationTestBase;

@DisplayName("[DocumentationTest] 주문 상세조회 실패 API 테스트")
class OrderDetailSearchFailureDocumentationTest extends DocumentationTestBase {

    @Test
    @DisplayName("주문 조회가 성공하면 200 OK가 반환된다.")
    void order_detail_search_failure_test() {
        Long invalidOrderId = 100_000L;

        given(this.specification)
            .filters(ORDER_DETAIL_SEARCH_FAILURE_HANDLER)
            .contentType(APPLICATION_JSON)

            .when()
            .pathParam("orderId", invalidOrderId)
            .get("/api/orders/{orderId}")

            .then()
            .statusCode(equalTo(404))
            .body(notNullValue());
    }
}
