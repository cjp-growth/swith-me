package project.swithme.order.test.order.documentationtest.query;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.RestAssured.given;
import static order.OrderFixture.createOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static project.swithme.domain.core.order.entity.OrderStatus.PAYMENT_REQUEST;
import static project.swithme.order.test.order.documentationtest.snippet.OrderSnippet.MY_ORDERS_SEARCH_DOCUMENT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.order.common.utils.Cursor;
import project.swithme.order.common.utils.CursorFactory;
import project.swithme.order.test.IntegrationTestBase;

@DisplayName("[DocumentationTest] 내 주문 목록 API 테스트")
class MyOrdersSearchDocumentationTest extends IntegrationTestBase {

    @Test
    @DisplayName("내 주문 목록 조회가 성공하면 200 OK가 반환된다.")
    void order_search_test() {
        Order newOrder = persistenceHelper.persist(createOrder(PAYMENT_REQUEST));
        Cursor cursor = CursorFactory.createCursor("10", "10");

        given(this.specification)
            .contentType(APPLICATION_JSON)
            .filters(MY_ORDERS_SEARCH_DOCUMENT)
            .get(
                "/api/orders/my-orders?index={index}&limit={limit}",
                cursor.getIndex(),
                cursor.getLimit()
            )

            .then()
            .statusCode(equalTo(HttpStatus.OK.value()))
            .body(notNullValue())
            .log()
            .all();
    }

    @Test
    @DisplayName("내 주문 목록 조회(첫 페이지)가 성공하면 200 OK가 반환된다.")
    void order_first_page_search_test() {
        Cursor cursor = CursorFactory.createCursor(null, "10");

        given(this.specification)
            .contentType(APPLICATION_JSON)
            .get(
                "/api/orders/my-orders?limit={limit}",
                cursor.getLimit()
            )

            .then()
            .statusCode(equalTo(HttpStatus.OK.value()))
            .body(notNullValue())
            .log()
            .all();
    }
}
