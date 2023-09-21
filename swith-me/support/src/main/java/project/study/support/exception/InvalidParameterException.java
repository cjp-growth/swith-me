package project.study.support.exception;

import java.util.List;
import lombok.Getter;
import project.study.support.codeandmessage.common.CommonErrorCodeAndMessage;
import project.study.support.log.Field;

@Getter
public class InvalidParameterException extends CommonException {

    public InvalidParameterException(List<Field> fields) {
        super(CommonErrorCodeAndMessage.INVALID_CLIENT_REQUEST, fields);
    }
}
