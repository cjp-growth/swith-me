package project.swithme.order.test.order.documentationtest.paymentgroup;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static project.swithme.order.test.order.documentationtest.snippet.payment.PaymentGroupSnippet.PAY_GROUP_LIST_SNIPPET;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import project.swithme.order.test.IntegrationTestBase;

@DisplayName("[DocumentationTest] 결제 그룹 리스트 조회 API 테스트")
class PayGroupListSearchDocumentationTest extends IntegrationTestBase {

    private final RestDocumentationFilter document = document(
        "{class_name}/{method_name}/",
        PAY_GROUP_LIST_SNIPPET
    );

    @Test
    @DisplayName("결제 그룹 목록을 조회하면 200 OK를 반환한다.")
    void pay_group_search_test() {
        given(this.specification)
            .filters(document)

            .when()
            .contentType(JSON)
            .get("/api/orders/pay-group")

            .then()
            .statusCode(equalTo(200))
            .body(notNullValue())
            .log()
            .all();
    }
}
