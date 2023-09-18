package project.swithme.payment.test.documentationtest.snippet.paymentgroup;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static project.swithme.payment.test.documentationtest.snippet.PaymentSnippet.PAY_TYPES_FILTER;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.payment.test.DocumentationTestBase;

@DisplayName("[DocumentationTest] 결제 수단 리스트 조회 API 테스트")
class PayTypesSearchDocumentationTest extends DocumentationTestBase {

    @Test
    @DisplayName("결제 수단 목록을 조회하면 200 OK를 반환한다.")
    void pay_types_search_test() {
        given(this.specification)
            .filters(PAY_TYPES_FILTER)

            .when()
            .queryParam("payType", "CARD")
            .get("/api/payments/pay-types")

            .then()
            .statusCode(equalTo(200))
            .body(notNullValue())
            .log()
            .all();
    }
}
