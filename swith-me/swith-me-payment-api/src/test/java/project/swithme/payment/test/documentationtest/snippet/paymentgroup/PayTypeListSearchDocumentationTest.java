package project.swithme.payment.test.documentationtest.snippet.paymentgroup;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.DisplayName;
import org.springframework.restdocs.restassured.RestAssuredRestDocumentation;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import project.swithme.payment.test.IntegrationTestBase;
import project.swithme.payment.test.documentationtest.snippet.payment.PayTypesSnippet;

@DisplayName("[DocumentationTest] 결제 그룹 리스트 조회 API 테스트")
class PayTypeListSearchDocumentationTest extends IntegrationTestBase {

    private RestDocumentationFilter document = RestAssuredRestDocumentation.document(
        "{class_name}/{method_name}/",
        PayTypesSnippet.PAY_TYPE_LIST_SNIPPET
    );

    // @Test
    @DisplayName("결제 수단 목록을 조회하면 200 OK를 반환한다.")
    void pay_types_search_test() {
        given(this.specification)
            .filters(document)

            .when()
            .contentType(JSON)
            .get("/api/orders?payGroup={payGroup}", "CASH")

            .then()
            .statusCode(equalTo(200))
            .body(notNullValue())
            .log()
            .all();
    }
}