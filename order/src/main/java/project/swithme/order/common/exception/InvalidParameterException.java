package project.swithme.order.common.exception;

import java.util.List;
import lombok.Getter;
import project.swithme.order.common.exception.error.ErrorField;

@Getter
public class InvalidParameterException extends RuntimeException {

    private static final String message = "올바른 값을 입력해주세요.";
    private final List<ErrorField> fields;

    public InvalidParameterException(List<ErrorField> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return String.format("fields: %s", fields);
    }
}
