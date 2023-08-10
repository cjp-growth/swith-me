package project.swithme.payment.test.documentationtest.snippet;

import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.NUMBER;
import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.QueryParametersSnippet;
import org.springframework.restdocs.snippet.Attributes;

public interface PaymentSnippet {

    QueryParametersSnippet PAYMENT_REQUEST_PARAMETERS = queryParameters(
        parameterWithName("orderId").description("주문 PK"),
        parameterWithName("paymentKey").description("결제 키"),
        parameterWithName("paymentType").description("결제 타입"),
        parameterWithName("amount").description("총 비용")
    );

    ResponseFieldsSnippet PAYMENT_COMPLETE_RESPONSE =
        responseFields(
            fieldWithPath("data.paymentId").type(NUMBER).description("결제 PK"),
            fieldWithPath("code").type(STRING).description("응답 코드"),
            fieldWithPath("message").type(STRING).description("응답 메시지"),
            fieldWithPath("time").type(STRING).description("응답 시간")
        );

    static Attributes.Attribute getAttribute(String field) {
        return Attributes.key(field)
            .value("");
    }
}
