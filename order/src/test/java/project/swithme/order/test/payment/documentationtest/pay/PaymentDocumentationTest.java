package project.swithme.order.test.payment.documentationtest.pay;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static project.swithme.order.common.testhelper.order.fixture.OrderFixture.createOrder;
import static project.swithme.order.test.order.documentationtest.snippet.OrderSnippet.AMOUNT;
import static project.swithme.order.test.order.documentationtest.snippet.OrderSnippet.PAYMENT_KEY;
import static project.swithme.order.test.order.documentationtest.snippet.OrderSnippet.PAYMENT_NORMAL;
import static project.swithme.order.test.payment.documentationtest.snippet.PaymentSnippet.PAYMENT_COMPLETE_RESPONSE;
import static project.swithme.order.test.payment.documentationtest.snippet.PaymentSnippet.PAYMENT_REQUEST_PARAMETERS;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.restassured.RestAssuredRestDocumentation;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.order.infrastructure.OrderJpaRepository;
import project.swithme.order.test.IntegrationTestBase;

@DisplayName("[DocumentationTest] 주문 생성 API 테스트")
class PaymentDocumentationTest extends IntegrationTestBase {

    private final String apiUrl = "/api/payments/toss?paymentType={paymentType}&orderId={orderId}&paymentKey={paymentKey}&amount={amount}";

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Test
    @DisplayName("주문이 성공하면 201 CREATED가 반환된다.")
    void order_create_test() {
        Order order = createOrder(null, UUID.randomUUID());
        Order newOrder = orderJpaRepository.save(order);

        given(this.specification)
            .filters(document())

            .when()
            .contentType(JSON)
            .get(apiUrl, PAYMENT_NORMAL, newOrder.getUniqueId(), PAYMENT_KEY, AMOUNT)

            .then()
            .statusCode(equalTo(201))
            .body(notNullValue())
            .log()
            .all();
    }

    private RestDocumentationFilter document() {
        return RestAssuredRestDocumentation.document(
            "{class_name}/{method_name}/",
            PAYMENT_REQUEST_PARAMETERS,
            PAYMENT_COMPLETE_RESPONSE
        );
    }
}
