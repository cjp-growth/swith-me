package project.swithme.order.test.order.documentationtest.query;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.RestAssured.given;
import static order.OrderFixture.createOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static project.swithme.domain.core.order.entity.OrderStatus.PAYMENT_REQUEST;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.order.test.IntegrationTestBase;

@DisplayName("[DocumentationTest] 주문 상세조회 API 테스트")
class OrderDetailSearchDocumentationTest extends IntegrationTestBase {

    @Test
    @DisplayName("주문 조회가 성공하면 200 OK가 반환된다.")
    void order_detail_search_test() {
        Order newOrder = persistenceHelper.persist(createOrder(PAYMENT_REQUEST));

        given(this.specification)
            .contentType(APPLICATION_JSON)
            .get("/api/orders/{orderId}", newOrder.getId())

            .then()
            .statusCode(equalTo(HttpStatus.OK.value()))
            .body(notNullValue())
            .log()
            .all();
    }
}
