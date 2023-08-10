package project.swithme.payment.core.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.study.support.response.codeandmessage.CodeAndMessage;
import project.swithme.payment.core.exception.PaymentCancelCodeAndMessage;
import project.swithme.payment.core.exception.PaymentErrorResponse;

@Component
@RequiredArgsConstructor
public class CodeAndMessageParser {

    private static final String DELIMETER = ": ";
    private static final int CODE_AND_MESSAGE = 1;

    private final ObjectMapper objectMapper;

    public CodeAndMessage parse(String errorMessage) {
        try {
            String[] errorInforArray = errorMessage.split(DELIMETER);
            PaymentErrorResponse[] responses = objectMapper.readValue(
                errorInforArray[CODE_AND_MESSAGE],
                PaymentErrorResponse[].class
            );
            return findCodeAndMessageByCode(Arrays.asList(responses));
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private CodeAndMessage findCodeAndMessageByCode(List<PaymentErrorResponse> errors) {
        return errors.stream()
            .map(error -> PaymentCancelCodeAndMessage.findCodeAndMessage(error.getCode()))
            .findFirst()
            .orElseThrow();
    }
}
