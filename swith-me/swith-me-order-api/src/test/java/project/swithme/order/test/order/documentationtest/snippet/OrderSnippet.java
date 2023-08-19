package project.swithme.order.test.order.documentationtest.snippet;

import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.BOOLEAN;
import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.NUMBER;
import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.STRING;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import java.util.List;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.QueryParametersSnippet;
import org.springframework.restdocs.restassured.RestAssuredRestDocumentation;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import org.springframework.restdocs.snippet.Attributes;
import project.swithme.domain.core.payment.entity.PaymentType;
import project.swithme.order.core.presentation.command.request.OrderCreateRequest;

public interface OrderSnippet {

    String PAYMENT_NORMAL = PaymentType.NORMAL.name();
    String PAYMENT_KEY = "vNA96Bjgq7XZYkKL4MrjOOEqnYGGk80zJwlEWR52xydGPnOQ";
    String AMOUNT = "130000";

    RequestFieldsSnippet ORDER_CREATE_REQUEST_FIELD_DESCRIPTOR = requestFields(
        fieldWithPath("studyCafeId").description("스터디 카페 PK")
            .attributes(getAttribute("studyCafeId")),
        fieldWithPath("studyCafeTicketId").description("상품(스터디 카페 이용권) PK")
            .attributes(getAttribute("studyCafeTicketId")),
        fieldWithPath("studyCafeTicketPrice").description("상품(스터디 카페 이용권 가격) 가격")
            .attributes(getAttribute("studyCafeTicketPrice")),
        fieldWithPath("lockerId").description("락커 PK")
            .attributes(getAttribute("lockerId")),
        fieldWithPath("lockerPrice").description("락커 가격")
            .attributes(getAttribute("lockerPrice")),
        fieldWithPath("title").description("주문명")
            .attributes(getAttribute("title")),
        fieldWithPath("payType").description("결제 수단")
            .attributes(getAttribute("payType"))
    );

    QueryParametersSnippet MY_ORDERS_SEARCH_QUERY_PARAM = queryParameters(
        parameterWithName("index").description("주문 PK"),
        parameterWithName("limit").description("페이지 크기"),
        parameterWithName("startDate").description("조회 시작 날짜").optional(),
        parameterWithName("endDate").description("조회 마지막 날짜").optional()
    );

    ResponseFieldsSnippet MY_ORDERS_SEARCH_RESPONSE =
        responseFields(
            fieldWithPath("data.hasNext").type(BOOLEAN).description("다음 페이지 유무"),
            fieldWithPath("data.size").type(NUMBER).description("데이터 크기"),
            fieldWithPath("data.orders[].orderId").type(NUMBER).description("주문 PK"),
            fieldWithPath("data.orders[].title").type(STRING).description("주문명"),
            fieldWithPath("data.orders[].orderStatus").type(STRING).description("주문 상태"),
            fieldWithPath("data.orders[].payType").type(STRING).description("결제 방법"),
            fieldWithPath("data.orders[].orderDate").type(STRING).description("주문 날짜"),
            fieldWithPath("code").type(STRING).description("응답 코드"),
            fieldWithPath("message").type(STRING).description("응답 메시지")
        );

    RestDocumentationFilter MY_ORDERS_SEARCH_DOCUMENT = RestAssuredRestDocumentation.document(
        "{class_name}/{method_name}/",
        MY_ORDERS_SEARCH_QUERY_PARAM,
        MY_ORDERS_SEARCH_RESPONSE
    );

    ResponseFieldsSnippet ORDER_CREATE_RESPONSE =
        responseFields(
            fieldWithPath("data.orderId").type(NUMBER).description("주문 PK"),
            fieldWithPath("code").type(STRING).description("응답 코드"),
            fieldWithPath("message").type(STRING).description("응답 메시지")
        );

    RestDocumentationResultHandler ORDER_CREATE_DOCUMENT = document(
        "{class_name}/{method_name}/",
        ORDER_CREATE_REQUEST_FIELD_DESCRIPTOR,
        ORDER_CREATE_RESPONSE
    );

    static Attributes.Attribute getAttribute(String field) {
        StringBuilder sb = new StringBuilder();
        ConstraintDescriptions constraints = new ConstraintDescriptions(OrderCreateRequest.class);

        List<String> fieldConstraints = constraints.descriptionsForProperty(field);
        for (String constraint : fieldConstraints) {
            sb.append("-")
                .append(" ")
                .append(constraint)
                .append("\n")
                .append("\n");
        }
        return Attributes.key(field)
            .value(sb.toString());
    }
}
