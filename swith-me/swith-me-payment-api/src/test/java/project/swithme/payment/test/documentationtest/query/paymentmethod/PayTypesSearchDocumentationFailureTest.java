package project.swithme.payment.test.documentationtest.query.paymentmethod;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static project.swithme.payment.test.documentationtest.snippet.PaymentSnippet.PAYMENT_TYPE_SEARCH_FAILURE_HANDLER;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.payment.test.DocumentationTestBase;

@DisplayName("[DocumentationTest] 결제 수단 리스트 조회 실패 API 테스트")
class PayTypesSearchDocumentationFailureTest extends DocumentationTestBase {

    @Test
    @DisplayName("올바르지 않은 결제 그룹을 입력하면 400 BAD_REQUEST를 반환한다.")
    void pay_types_search_failure_test() {
        given(this.specification)
            .filters(PAYMENT_TYPE_SEARCH_FAILURE_HANDLER)

            .when()
            .queryParam("payType", "INVALID_PARAMETER")
            .get("/api/payments/pay-types")

            .then()
            .statusCode(equalTo(400))
            .body(notNullValue())
            .log()
            .all();
    }
}
