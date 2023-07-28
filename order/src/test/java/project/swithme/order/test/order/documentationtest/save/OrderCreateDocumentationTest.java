package project.swithme.order.test.order.documentationtest.save;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static project.swithme.order.test.order.documentationtest.snippet.OrderSnippet.ORDER_CREATE_REQUEST_FIELD_DESCRIPTOR;
import static project.swithme.order.test.order.documentationtest.snippet.OrderSnippet.ORDER_CREATE_RESPONSE;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import project.swithme.order.core.web.order.presentation.request.OrderRequest;
import project.swithme.order.test.IntegrationTestBase;

@DisplayName("[DocumentationTest] 주문 생성 API 테스트")
class OrderCreateDocumentationTest extends IntegrationTestBase {

    RestDocumentationFilter documents = document(
        "{class_name}/{method_name}/",
        ORDER_CREATE_REQUEST_FIELD_DESCRIPTOR,
        ORDER_CREATE_RESPONSE
    );

    @Test
    @DisplayName("주문이 성공하면 201 CREATED가 반환된다.")
    void order_create_test() throws Exception {
        OrderRequest request = createOrderCreateRequest();

        given(this.specification)
            .filters(documents)

            .when()
            .contentType(JSON)
            .body(objectMapper.writeValueAsString(request))
            .post("/api/orders")

            .then()
            .statusCode(equalTo(201))
            .body(notNullValue())
            .log()
            .all();
    }

    private OrderRequest createOrderCreateRequest() {
        return new OrderRequest(
            1L,
            1L,
            BigDecimal.valueOf(100_000),
            1L,
            BigDecimal.valueOf(30_000),
            "TOSS"
        );
    }
}
