package project.study.support.exception;

import static project.study.support.codeandmessage.common.CommonErrorCodeAndMessage.UN_AUTHORIZED;

public class UnAuthorizedException extends DomainException {

    public UnAuthorizedException() {
        super(UN_AUTHORIZED);
    }
}
