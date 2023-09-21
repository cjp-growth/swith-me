package project.swithme.order.test.order.documentationtest.snippet;

import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.BOOLEAN;
import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.NUMBER;
import static javax.xml.xpath.XPathEvaluationResult.XPathResultType.STRING;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper;
import java.util.List;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import org.springframework.restdocs.snippet.Attributes;
import project.swithme.order.core.presentation.command.request.OrderCreateRequest;

public interface OrderSnippet {

    String IDENTIFIER = "{class_name}/{method_name}/";

    FieldDescriptor[] ORDER_CREATE_REQUEST_FIELDS = {
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
    };

    FieldDescriptor[] ORDER_CREATE_REQUEST_RESPONSE = {
        fieldWithPath("data.orderId").type(NUMBER).description("주문 PK"),
        fieldWithPath("code").type(STRING).description("응답 코드"),
        fieldWithPath("message").type(STRING).description("응답 메시지")
    };

    FieldDescriptor[] ORDER_ERROR_RESPONSE = new FieldDescriptor[]{
        fieldWithPath("code").type(STRING).description("응답 코드"),
        fieldWithPath("message").type(STRING).description("응답 메시지")
    };

    FieldDescriptor[] ORDER_DETAIL_SEARCH_RESPONSE = new FieldDescriptor[]{
        fieldWithPath("data.orderId").type(NUMBER).description("응답 코드"),
        fieldWithPath("data.title").type(STRING).description("주문명"),
        fieldWithPath("data.orderStatus").type(STRING).description("주문 상태"),
        fieldWithPath("data.payType").type(STRING).description("결제 수단"),
        fieldWithPath("data.orderDate").type(STRING).description("주문 날짜"),
        fieldWithPath("code").type(STRING).description("응답 코드"),
        fieldWithPath("message").type(STRING).description("응답 메시지")
    };

    ParameterDescriptor ORDER_PATH_DESCRIPTOR =
        parameterWithName("orderId").description("주문 PK");

    ParameterDescriptor[] MY_ORDERS_SEARCH_QUERY_PARAM = {
        parameterWithName("index").description("주문 PK"),
        parameterWithName("limit").description("페이지 크기"),
        parameterWithName("startDate").description("조회 시작 날짜").optional(),
        parameterWithName("endDate").description("조회 마지막 날짜").optional()
    };

    FieldDescriptor[] MY_ORDERS_SEARCH_RESPONSE = {
        fieldWithPath("data.hasNext").type(BOOLEAN).description("다음 페이지 유무"),
        fieldWithPath("data.size").type(NUMBER).description("데이터 크기"),
        fieldWithPath("data.orders[].orderId").type(NUMBER).description("주문 PK"),
        fieldWithPath("data.orders[].title").type(STRING).description("주문명"),
        fieldWithPath("data.orders[].orderStatus").type(STRING).description("주문 상태"),
        fieldWithPath("data.orders[].payType").type(STRING).description("결제 수단"),
        fieldWithPath("data.orders[].orderDate").type(STRING).description("주문 날짜"),
        fieldWithPath("code").type(STRING).description("응답 코드"),
        fieldWithPath("message").type(STRING).description("응답 메시지")
    };

    ResponseFieldsSnippet ORDER_ERROR_RESPONSE_SNIPPET = responseFields(ORDER_ERROR_RESPONSE);
    RestDocumentationFilter MY_ORDERS_SEARCH_FAILURE_FILTER =
        RestAssuredRestDocumentationWrapper.document(
            IDENTIFIER,
            ResourceSnippetParameters.builder()
                .queryParameters(MY_ORDERS_SEARCH_QUERY_PARAM)
                .responseFields(ORDER_ERROR_RESPONSE)
                .tags("Order")
                .summary("내 주문 목록 API")
                .privateResource(false)
                .deprecated(false)
                .description("내 주문 목록 조회 API 입니다."),
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            queryParameters(MY_ORDERS_SEARCH_QUERY_PARAM),
            ORDER_ERROR_RESPONSE_SNIPPET
        );
    RestDocumentationFilter ORDER_DETAIL_SEARCH_FAILURE_HANDLER =
        RestAssuredRestDocumentationWrapper.document(
            IDENTIFIER,
            ResourceSnippetParameters.builder()
                .pathParameters(ORDER_PATH_DESCRIPTOR)
                .responseFields(ORDER_ERROR_RESPONSE)
                .tags("Order")
                .summary("주문 API")
                .privateResource(false)
                .deprecated(false)
                .description("주문 상세 조회 API 입니다."),
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            pathParameters(ORDER_PATH_DESCRIPTOR),
            ORDER_ERROR_RESPONSE_SNIPPET
        );
    RestDocumentationFilter MY_ORDERS_SEARCH_FILTER =
        RestAssuredRestDocumentationWrapper.document(
            IDENTIFIER,
            ResourceSnippetParameters.builder()
                .queryParameters(MY_ORDERS_SEARCH_QUERY_PARAM)
                .responseFields(MY_ORDERS_SEARCH_RESPONSE)
                .tags("Order")
                .summary("내 주문 목록 API")
                .privateResource(false)
                .deprecated(false)
                .description("내 주문 목록 조회 API 입니다."),
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            queryParameters(MY_ORDERS_SEARCH_QUERY_PARAM),
            responseFields(MY_ORDERS_SEARCH_RESPONSE)
        );
    RestDocumentationResultHandler ORDER_CREATE_REQUEST_HANDLER =
        MockMvcRestDocumentationWrapper.document(
            IDENTIFIER,
            ResourceSnippetParameters.builder()
                .requestFields(ORDER_CREATE_REQUEST_FIELDS)
                .responseFields(ORDER_CREATE_REQUEST_RESPONSE)
                .tags("Order")
                .summary("주문 API")
                .privateResource(false)
                .deprecated(false)
                .description("주문 API 입니다."),
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            requestFields(ORDER_CREATE_REQUEST_FIELDS),
            responseFields(ORDER_CREATE_REQUEST_RESPONSE)
        );
    RestDocumentationFilter ORDER_DETAIL_SEARCH_HANDLER =
        RestAssuredRestDocumentationWrapper.document(
            IDENTIFIER,
            ResourceSnippetParameters.builder()
                .pathParameters(ORDER_PATH_DESCRIPTOR)
                .responseFields(ORDER_DETAIL_SEARCH_RESPONSE)
                .tags("Order")
                .summary("주문 API")
                .privateResource(false)
                .deprecated(false)
                .description("주문 상세 조회 API 입니다."),
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            pathParameters(ORDER_PATH_DESCRIPTOR),
            responseFields(ORDER_DETAIL_SEARCH_RESPONSE)
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
