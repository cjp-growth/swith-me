package project.swithme.payment.test.documentationtest.snippet;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.STRING;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper;
import javax.management.openmbean.SimpleType;
import javax.swing.text.html.parser.DTDConstants;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.restassured.RestDocumentationFilter;

public interface PaymentSnippet {

    String identifier = "{class_name}/{method_name}/";

    FieldDescriptor[] PAYMENT_ERROR_RESPONSE = new FieldDescriptor[]{
        fieldWithPath("code").type(STRING).description("응답 코드"),
        fieldWithPath("message").type(STRING).description("응답 메시지")
    };

    FieldDescriptor[] PAY_GROUPS_RESPONSE_FIELDS =
        new FieldDescriptor[]{
            fieldWithPath("data.payGroups").type(STRING).description("결제 그룹 목록"),
            fieldWithPath("code").type(STRING).description("응답 코드"),
            fieldWithPath("message").type(STRING).description("응답 메시지")
        };

    RestDocumentationFilter PAY_GROUPS_FILTER =
        RestAssuredRestDocumentationWrapper.document(
            identifier,
            ResourceSnippetParameters.builder()
                .responseFields(PAY_GROUPS_RESPONSE_FIELDS)
                .tags("Payment")
                .summary("결제 그룹 목록 API")
                .privateResource(false)
                .deprecated(false)
                .description("결제 타입 목록 API 입니다."),
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint())
        );

    ParameterDescriptor[] PAY_TYPE_SEARCH_QUERY_PARAMETERS =
        new ParameterDescriptor[]{parameterWithName("payType").description("결제 타입")};

    FieldDescriptor[] PAY_TYPES_RESPONSE_FIELDS = new FieldDescriptor[]{
        fieldWithPath("data.payTypes").type(STRING).description("결제 수단 목록"),
        fieldWithPath("code").type(STRING).description("응답 코드"),
        fieldWithPath("message").type(STRING).description("응답 메시지")
    };

    RestDocumentationFilter PAY_TYPES_FILTER =
        RestAssuredRestDocumentationWrapper.document(
            identifier,
            ResourceSnippetParameters.builder()
                .queryParameters(PAY_TYPE_SEARCH_QUERY_PARAMETERS)
                .responseFields(PAY_TYPES_RESPONSE_FIELDS)
                .tags("Payment")
                .summary("결제 타입 목록 API")
                .privateResource(false)
                .deprecated(false)
                .description("결제 타입 목록 API 입니다."),
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            queryParameters(PAY_TYPE_SEARCH_QUERY_PARAMETERS)
        );

    ParameterDescriptor[] TOSS_CARD_PAYMENT_QUERY_PARAMETERS = new ParameterDescriptor[]{
        parameterWithName("orderId").description("주문 PK"),
        parameterWithName("paymentKey").description("결제 키"),
        parameterWithName("paymentType").description("결제 타입"),
        parameterWithName("amount").description("총 비용")
    };

    FieldDescriptor[] TOSS_CARD_PAYMENT_RESPONSE_FIELDS = new FieldDescriptor[]{
        fieldWithPath("data.paymentId").type(DTDConstants.NUMBER).description("결제 PK"),
        fieldWithPath("code").type(SimpleType.STRING).description("응답 코드"),
        fieldWithPath("message").type(SimpleType.STRING).description("응답 메시지")
    };

    RestDocumentationResultHandler TOSS_CARD_PAYMENT_HANDLER =
        MockMvcRestDocumentationWrapper.document(
            identifier,
            ResourceSnippetParameters.builder()
                .queryParameters(TOSS_CARD_PAYMENT_QUERY_PARAMETERS)
                .responseFields(TOSS_CARD_PAYMENT_RESPONSE_FIELDS)
                .tags("Payment")
                .summary("토스 카드 결제 API")
                .privateResource(false)
                .deprecated(false)
                .description("토스 카드 결제 API 입니다."),
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            queryParameters(TOSS_CARD_PAYMENT_QUERY_PARAMETERS)
        );

    RestDocumentationResultHandler TOSS_CARD_PAYMENT_FAILURE_HANDLER =
        MockMvcRestDocumentationWrapper.document(
            identifier,
            ResourceSnippetParameters.builder()
                .queryParameters(TOSS_CARD_PAYMENT_QUERY_PARAMETERS)
                .responseFields(PAYMENT_ERROR_RESPONSE)
                .tags("Payment")
                .summary("토스 카드 결제 API")
                .privateResource(false)
                .deprecated(false)
                .description("토스 카드 결제 API 입니다."),
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            queryParameters(TOSS_CARD_PAYMENT_QUERY_PARAMETERS)
        );

    ParameterDescriptor[] TOSS_CARD_PAYMENT_CANCEL_QUERY_PARAMETERS = new ParameterDescriptor[]{
        parameterWithName("paymentKey").description("결제 키"),
        parameterWithName("cancelReason").description("주문 취소 이유")
    };

    FieldDescriptor[] PAYMENT_CANCEL_RESPONSE_FIELDS = new FieldDescriptor[]{
        fieldWithPath("data").type(STRING).description("응답 데이터"),
        fieldWithPath("code").type(STRING).description("응답 코드"),
        fieldWithPath("message").type(STRING).description("응답 메시지")
    };

    RestDocumentationResultHandler TOSS_CARD_PAYMENT_CANCEL_HANDLER =
        document(
            identifier,
            ResourceSnippetParameters.builder()
                .queryParameters(TOSS_CARD_PAYMENT_CANCEL_QUERY_PARAMETERS)
                .responseFields(PAYMENT_CANCEL_RESPONSE_FIELDS)
                .tags("Payment")
                .summary("토스 카드 결제 취소 API")
                .privateResource(false)
                .deprecated(false)
                .description("토스 카드 결제 취소 API 입니다."),
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            queryParameters(TOSS_CARD_PAYMENT_CANCEL_QUERY_PARAMETERS)
        );

    RestDocumentationFilter PAYMENT_TYPE_SEARCH_FAILURE_HANDLER =
        RestAssuredRestDocumentationWrapper.document(
            identifier,
            ResourceSnippetParameters.builder()
                .queryParameters(PAY_TYPE_SEARCH_QUERY_PARAMETERS)
                .responseFields(PAYMENT_ERROR_RESPONSE)
                .tags("Payment")
                .summary("결제 타입 목록 API")
                .privateResource(false)
                .deprecated(false)
                .description("결제 타입 목록 API 입니다."),
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            queryParameters(PAY_TYPE_SEARCH_QUERY_PARAMETERS)
        );
}
