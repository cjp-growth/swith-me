package project.swithme.commonapi.test.common;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import project.study.support.codeandmessage.common.CommonErrorCodeAndMessage;
import project.swithme.commonapi.ErrorCodeAndMessageTest;

@DisplayName("[DocumentationTest] 공통 에러코드 API 테스트")
class CommonErrorCodeAndMessagesDocumentationTest extends ErrorCodeAndMessageTest {

    @Test
    @DisplayName("공통 에러 응답 코드를 조회하면 200 OK를 반환한다.")
    void common_error_code_response_test() throws Exception {
        this.mockMvc.perform(get("/api/specs/errors/common")
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(document(
                identifier,
                getCodeAndMessageResponseFieldsSnippet(
                    "error-code-response",
                    beneathPath(beneathPath),
                    null,
                    convertToFieldDescriptorArray(CommonErrorCodeAndMessage.values())
                )
            ))
            .andExpect(status().isOk());
    }
}
