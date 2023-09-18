package project.study.support.exception;

import project.study.support.codeandmessage.common.CommonErrorCodeAndMessage;

public class ApiSpecUnMatchedException extends CommonException {

    public ApiSpecUnMatchedException(
        CommonErrorCodeAndMessage commonErrorCodeAndMessage,
        String message
    ) {
        super(commonErrorCodeAndMessage);
    }
}
