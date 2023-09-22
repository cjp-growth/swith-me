package project.swithme.commonapi;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.restdocs.snippet.Attributes.Attribute;
import org.springframework.test.web.servlet.MockMvc;
import project.study.support.codeandmessage.CodeAndMessage;
import project.swithme.apicommon.ApiCommonApplication;
import project.swithme.commonapi.common.snippet.CodeAndMessageResponseFieldsSnippet;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(classes = ApiCommonApplication.class)
public abstract class ErrorCodeAndMessageTest {

    protected final String beneathPath = "errorCodes";
    protected final String identifier = "{class-name}/{method-name}";
    protected final String code = "code";
    protected final String engErrorMessage = "engErrorMessage";

    @Autowired
    protected MockMvc mockMvc;

    protected CodeAndMessageResponseFieldsSnippet getCodeAndMessageResponseFieldsSnippet(
        String type,
        PayloadSubsectionExtractor<?> subsectionExtractor,
        Map<String, Object> attributes,
        FieldDescriptor... descriptors
    ) {
        return new CodeAndMessageResponseFieldsSnippet(
            type,
            subsectionExtractor,
            Arrays.asList(descriptors),
            attributes,
            true
        );
    }

    protected FieldDescriptor[] convertToFieldDescriptorArray(
        CodeAndMessage[] errorCodeAndMessages
    ) {
        return Arrays.stream(errorCodeAndMessages)
            .map(this::getFieldDescriptor)
            .toArray(FieldDescriptor[]::new);
    }

    protected FieldDescriptor getFieldDescriptor(CodeAndMessage codeAndMessages) {
        return fieldWithPath(codeAndMessages.getErrorCode())
            .description(codeAndMessages.getKrErrorMessage())
            .attributes(
                getAttribute(code, codeAndMessages.getStatusCode()),
                getAttribute(engErrorMessage, codeAndMessages.getEnErrorMessage())
            )
            .type(JsonFieldType.OBJECT);
    }

    protected Attribute getAttribute(
        String key,
        Object value
    ) {
        return key(key)
            .value(value);
    }
}
