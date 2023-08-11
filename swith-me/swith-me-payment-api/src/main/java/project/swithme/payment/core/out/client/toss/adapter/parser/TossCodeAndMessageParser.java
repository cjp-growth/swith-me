package project.swithme.payment.core.out.client.toss.adapter.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import project.study.support.codeandmessage.CodeAndMessage;
import project.study.support.codeandmessage.out.toss.TossPaymentApprovalCodeAndMessage;
import project.study.support.codeandmessage.out.toss.TossPaymentCancelCodeAndMessage;
import project.study.support.codeandmessage.parser.CodeAndMessageParser;
import project.study.support.exception.ErrorMessageParseException;
import project.swithme.payment.core.out.client.toss.adapter.response.failure.TossPaymentErrorResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class TossCodeAndMessageParser implements CodeAndMessageParser {

    private static final String DELIMETER = ": ";
    private static final int CODE_AND_MESSAGE = 1;

    private final ObjectMapper objectMapper;

    @Override
    public CodeAndMessage parseRequestApprovalFailureMessage(String errorMessage) {
        try {
            TossPaymentErrorResponse[] responses = getResponse(errorMessage);
            return findApprovalCodeAndMessageByCode(Arrays.asList(responses));
        } catch (
            IndexOutOfBoundsException | JsonProcessingException | IllegalArgumentException exception
        ) {
            throw new ErrorMessageParseException(exception.getMessage());
        }
    }

    @Override
    public CodeAndMessage parsePaymentCancelFailureMessage(String errorMessage) {
        try {
            TossPaymentErrorResponse[] responses = getResponse(errorMessage);
            return findFailureCodeAndMessageByCode(Arrays.asList(responses));
        } catch (
            IndexOutOfBoundsException | JsonProcessingException | IllegalArgumentException exception
        ) {
            throw new ErrorMessageParseException(exception.getMessage());
        }
    }

    private TossPaymentErrorResponse[] getResponse(
        String errorMessage
    ) throws JsonProcessingException {
        String[] errorInforArray = errorMessage.split(DELIMETER);
        return objectMapper.readValue(
            errorInforArray[CODE_AND_MESSAGE],
            TossPaymentErrorResponse[].class
        );
    }

    private CodeAndMessage findApprovalCodeAndMessageByCode(List<TossPaymentErrorResponse> errors) {
        return errors.stream()
            .map(error -> TossPaymentApprovalCodeAndMessage.findCodeAndMessage(error.getCode()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("매핑된 에러 메시지를 찾을 수 없습니다."));
    }

    private CodeAndMessage findFailureCodeAndMessageByCode(List<TossPaymentErrorResponse> errors) {
        return errors.stream()
            .map(error -> TossPaymentCancelCodeAndMessage.findCodeAndMessage(error.getCode()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("매핑된 에러 메시지를 찾을 수 없습니다."));
    }
}
