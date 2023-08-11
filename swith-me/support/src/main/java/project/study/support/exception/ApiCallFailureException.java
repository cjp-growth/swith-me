package project.study.support.exception;

import static project.study.support.codeandmessage.common.CommonErrorCodeAndMessage.BAD_GATEWAY;

public class ApiCallFailureException extends CommonException {

    public ApiCallFailureException(String message) {
        super(BAD_GATEWAY, message);
    }
}
