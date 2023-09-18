package project.swithme.payment.test.documentationtest.query.paymentmethod;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static project.swithme.payment.test.documentationtest.snippet.PaymentSnippet.PAY_GROUPS_FILTER;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.payment.test.DocumentationTestBase;

@DisplayName("[DocumentationTest] 결제 그룹 리스트 조회 API 테스트")
class PayGroupsSearchDocumentationTest extends DocumentationTestBase {

     @Test
    @DisplayName("결제 그룹 목록을 조회하면 200 OK를 반환한다.")
    void pay_group_search_test() {
        given(this.specification)
            .filters(PAY_GROUPS_FILTER)

            .when()
            .contentType(JSON)
            .get("/api/payments/pay-group")

            .then()
            .statusCode(equalTo(200))
            .body(notNullValue())
            .log()
            .all();
    }
}
