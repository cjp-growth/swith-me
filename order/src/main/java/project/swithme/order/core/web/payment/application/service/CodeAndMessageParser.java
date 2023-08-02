package project.swithme.order.core.web.payment.application.service;

import static project.swithme.order.common.exception.error.TossPaymentCancelCodeAndMessage.findCodeAndMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.swithme.order.common.exception.error.CodeAndMessage;
import project.swithme.order.common.response.TossPaymentErrorResponse;

@Component
@RequiredArgsConstructor
public class CodeAndMessageParser {

    private static final String DELIMETER = ": ";
    private static final int CODE_AND_MESSAGE = 1;

    private final ObjectMapper objectMapper;

    public CodeAndMessage parse(String errorMessage) {
        try {
            String[] errorInforArray = errorMessage.split(DELIMETER);
            TossPaymentErrorResponse[] responses = objectMapper.readValue(
                errorInforArray[CODE_AND_MESSAGE],
                TossPaymentErrorResponse[].class
            );
            return findCodeAndMessageByCode(Arrays.asList(responses));
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private CodeAndMessage findCodeAndMessageByCode(List<TossPaymentErrorResponse> errors) {
        return errors.stream()
            .map(error -> findCodeAndMessage(error.getCode()))
            .findFirst()
            .orElseThrow();
    }
}
