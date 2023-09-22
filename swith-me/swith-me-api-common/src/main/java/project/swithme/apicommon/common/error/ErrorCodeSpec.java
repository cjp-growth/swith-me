package project.swithme.apicommon.common.error;

import lombok.Getter;
import project.study.support.codeandmessage.CodeAndMessage;

@Getter
public class ErrorCodeSpec {

    private int statusCode;
    private String errorCode;
    private String korErrorMessage;
    private String engErrorMessage;

    public ErrorCodeSpec(CodeAndMessage codeAndMessage) {
        this.statusCode = codeAndMessage.getStatusCode();
        this.errorCode = codeAndMessage.getErrorCode();
        this.korErrorMessage = codeAndMessage.getKrErrorMessage();
        this.engErrorMessage = codeAndMessage.getEnErrorMessage();
    }
}
