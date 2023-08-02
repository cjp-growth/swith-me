package project.swithme.order.common.utils;

import static project.swithme.order.common.exception.error.PaymentCancelCodeAndMessage.findCodeAndMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import project.swithme.order.common.exception.error.CodeAndMessage;
import project.swithme.order.common.response.PaymentErrorResponse;

public class CodeAndMessageParser {

    private static final String DELIMETER = ": ";
    private static final int CODE_AND_MESSAGE = 1;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static CodeAndMessage parse(String errorMessage) {
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

    private static CodeAndMessage findCodeAndMessageByCode(List<PaymentErrorResponse> errors) {
        return errors.stream()
            .map(error -> findCodeAndMessage(error.getCode()))
            .findFirst()
            .orElseThrow();
    }
}
