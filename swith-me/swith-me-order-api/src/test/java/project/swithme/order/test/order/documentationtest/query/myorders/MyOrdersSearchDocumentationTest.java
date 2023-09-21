package project.swithme.order.test.order.documentationtest.query.myorders;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static project.swithme.order.test.order.documentationtest.snippet.OrderSnippet.MY_ORDERS_SEARCH_FILTER;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.order.common.utils.Cursor;
import project.swithme.order.common.utils.CursorFactory;
import project.swithme.order.test.DocumentationTestBase;

@DisplayName("[DocumentationTest] 내 주문 목록 조회 성공 API 테스트")
class MyOrdersSearchDocumentationTest extends DocumentationTestBase {

    @Test
    @DisplayName("내 주문 목록 조회가 성공하면 200 OK가 반환된다.")
    void order_search_test() {
        persistenceHelper.persistAll();
        String index = "100";
        Cursor cursor = CursorFactory.createCursor(index, "10");
        LocalDate startDate = LocalDate.of(2020, 10, 23);
        LocalDate endDate = LocalDate.now()
            .plusDays(10);

        given(this.specification)
            .filters(MY_ORDERS_SEARCH_FILTER)
            .contentType(APPLICATION_JSON)

            .when()
            .queryParam("index", cursor.getIndex())
            .queryParam("limit", cursor.getLimit())
            .queryParam("startDate", startDate.toString())
            .queryParam("endDate", endDate.toString())
            .get("/api/orders/my-orders")

            .then()
            .statusCode(equalTo(200))
            .body(notNullValue());
    }
}
