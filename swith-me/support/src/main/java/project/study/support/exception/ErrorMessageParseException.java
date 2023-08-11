package project.study.support.exception;

import static project.study.support.codeandmessage.common.CommonErrorCodeAndMessage.API_SPEC_UN_MATCHED;

public class ErrorMessageParseException extends CommonException {

    public ErrorMessageParseException(String message) {
        super(API_SPEC_UN_MATCHED, message);
    }
}
