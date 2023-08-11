package project.swithme.payment.test.documentationtest.snippet;

import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.NUMBER;
import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.STRING;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.QueryParametersSnippet;
import org.springframework.restdocs.snippet.Attributes;

public interface PaymentSnippet {

    ResponseFieldsSnippet PAYMENT_COMPLETE_RESPONSE =
        responseFields(
            fieldWithPath("data.paymentId").type(NUMBER).description("결제 PK"),
            fieldWithPath("code").type(STRING).description("응답 코드"),
            fieldWithPath("message").type(STRING).description("응답 메시지")
        );

    static RestDocumentationResultHandler getOrderCreateRequestParams() {
        return document("{class-name}/{method-name}",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            getQueryParametersSnippet(),
            PAYMENT_COMPLETE_RESPONSE
        );
    }

    private static QueryParametersSnippet getQueryParametersSnippet() {
        return queryParameters(
            parameterWithName("orderId").description("주문 PK"),
            parameterWithName("paymentKey").description("결제 키"),
            parameterWithName("paymentType").description("결제 타입"),
            parameterWithName("amount").description("총 비용")
        );
    }

    static Attributes.Attribute getAttribute(String field) {
        return Attributes.key(field)
            .value("");
    }
}
