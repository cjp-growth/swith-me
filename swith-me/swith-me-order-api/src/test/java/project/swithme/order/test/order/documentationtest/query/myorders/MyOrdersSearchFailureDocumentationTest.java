package project.swithme.order.test.order.documentationtest.query.myorders;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static project.swithme.order.test.order.documentationtest.snippet.OrderSnippet.MY_ORDERS_SEARCH_FAILURE_FILTER;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.order.common.utils.Cursor;
import project.swithme.order.common.utils.CursorFactory;
import project.swithme.order.test.DocumentationTestBase;

@DisplayName("[DocumentationTest] 내 주문 목록 조회 실패 API 테스트")
class MyOrdersSearchFailureDocumentationTest extends DocumentationTestBase {

    @Test
    @DisplayName("내 주문목록을 조회할 때 시작일이 마감일 보다 늦다면 400 BAD_REQUEST 반환된다.")
    void order_first_page_search_test() {
        Cursor cursor = CursorFactory.createCursor("10", "10");
        LocalDate startDate = LocalDate.now()
            .plusYears(10);
        LocalDate endDate = LocalDate.now();

        given(this.specification)
            .filters(MY_ORDERS_SEARCH_FAILURE_FILTER)
            .contentType(APPLICATION_JSON)

            .when()
            .queryParam("index", cursor.getIndex())
            .queryParam("limit", cursor.getLimit())
            .queryParam("startDate", startDate.toString())
            .queryParam("endDate", endDate.toString())
            .get("/api/orders/my-orders")

            .then()
            .statusCode(equalTo(400))
            .body(notNullValue());

    }
}
