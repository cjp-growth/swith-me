package project.swithme.order.test.order.documentation_test.snippet.payment;

import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public interface PaymentGroupSnippet {

    ResponseFieldsSnippet PAY_GROUP_LIST_SNIPPET =
            responseFields(
                    fieldWithPath("data.payGroups").type(STRING).description("결제 그룹 목록"),
                    fieldWithPath("code").type(STRING).description("응답 코드"),
                    fieldWithPath("message").type(STRING).description("응답 메시지"),
                    fieldWithPath("time").type(STRING).description("응답 시간")
            );
}
