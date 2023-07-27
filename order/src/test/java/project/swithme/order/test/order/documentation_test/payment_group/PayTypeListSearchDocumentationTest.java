package project.swithme.order.test.order.documentation_test.payment_group;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import project.swithme.order.test.IntegrationTestBase;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static project.swithme.order.test.order.documentation_test.snippet.payment.PayTypesSnippet.PAY_TYPE_LIST_SNIPPET;

@DisplayName("[DocumentationTest] 결제 그룹 리스트 조회 API 테스트")
class PayTypeListSearchDocumentationTest extends IntegrationTestBase {

    @Test
    @DisplayName("결제 수단 목록을 조회하면 200 OK를 반환한다.")
    void pay_types_search_test() {
        given(this.specification)
                .filters(document)

                .when()
                .contentType(JSON)
                .get("/api/orders?pay-group={payGroup}", "CASH")

                .then()
                .statusCode(equalTo(200))
                .body(notNullValue())
                .log()
                .all();
    }

    private RestDocumentationFilter document = document(
            "{class_name}/{method_name}/",
            PAY_TYPE_LIST_SNIPPET
    );
}
