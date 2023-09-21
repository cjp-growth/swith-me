package project.swithme.order.test.order.documentationtest.query.detail;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.RestAssured.given;
import static order.OrderFixture.createOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static project.swithme.domain.core.order.entity.OrderStatus.PAYMENT_REQUEST;
import static project.swithme.order.test.order.documentationtest.snippet.OrderSnippet.ORDER_DETAIL_SEARCH_HANDLER;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.order.test.DocumentationTestBase;

@DisplayName("[DocumentationTest] 주문 상세조회 성공 API 테스트")
class OrderDetailSearchDocumentationTest extends DocumentationTestBase {

    @Test
    @DisplayName("주문 조회가 성공하면 200 OK가 반환된다.")
    void order_detail_search_test() {
        Order newOrder = persistenceHelper.persist(createOrder(PAYMENT_REQUEST));

        given(this.specification)
            .filters(ORDER_DETAIL_SEARCH_HANDLER)
            .contentType(APPLICATION_JSON)

            .when()
            .pathParam("orderId", newOrder.getId())
            .get("/api/orders/{orderId}")

            .then()
            .statusCode(equalTo(200))
            .body(notNullValue());
    }
}
