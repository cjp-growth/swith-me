package project.swithme.order.test.order.documentationtest.snippet;

import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.NUMBER;
import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import java.util.List;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.snippet.Attributes;
import project.swithme.order.core.web.order.presentation.request.OrderRequest;

public interface OrderSnippet {

    RequestFieldsSnippet ORDER_CREATE_REQUEST_FIELD_DESCRIPTOR = requestFields(
        fieldWithPath("studyCafeId").description("스터디 카페 PK")
            .attributes(getAttribute("studyCafeId")),
        fieldWithPath("productId").description("상품 PK")
            .attributes(getAttribute("productId")),
        fieldWithPath("productPrice").description("상품 가격")
            .attributes(getAttribute("productPrice")),
        fieldWithPath("lockerId").description("락커 PK")
            .attributes(getAttribute("lockerId")),
        fieldWithPath("lockerPrice").description("락커 가격")
            .attributes(getAttribute("lockerPrice")),
        fieldWithPath("payType").description("결제 수단")
            .attributes(getAttribute("payType"))
    );
    ResponseFieldsSnippet ORDER_CREATE_RESPONSE =
        responseFields(
            fieldWithPath("data.orderId").type(NUMBER).description("주문 PK"),
            fieldWithPath("code").type(STRING).description("응답 코드"),
            fieldWithPath("message").type(STRING).description("응답 메시지"),
            fieldWithPath("time").type(STRING).description("응답 시간")
        );

    static Attributes.Attribute getAttribute(String field) {
        StringBuilder sb = new StringBuilder();
        ConstraintDescriptions constraints = new ConstraintDescriptions(OrderRequest.class);

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
