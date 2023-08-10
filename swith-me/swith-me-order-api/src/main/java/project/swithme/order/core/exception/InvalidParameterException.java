package project.swithme.order.core.exception;

import java.util.List;
import lombok.Getter;
import project.study.support.log.Field;

@Getter
public class InvalidParameterException extends RuntimeException {

    private static final String message = "올바른 값을 입력해주세요.";
    private final List<Field> fields;

    public InvalidParameterException(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return String.format("fields: %s", fields);
    }
}
