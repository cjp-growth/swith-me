package project.study.support.codeandmessage.common;

import project.study.support.codeandmessage.CodeAndMessage;

public enum CommonErrorCodeAndMessage implements CodeAndMessage {
    INVALID_REQUEST(400, "올바르지 않은 요청입니다."),
    API_SPEC_UN_MATCHED(400, "외부 서버 API 스펙과 매핑을 확인해주세요."),
    INVALID_PATH_VARIABLE(400, "올바른 경로를 확인해주세요."),
    UN_AUTHORIZED(401, "권한이 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류입니다."),
    BAD_GATEWAY(502, "외부 서버와 통신하는 과정에서 오류가 발생했습니다.");

    private final int code;
    private final String message;

    CommonErrorCodeAndMessage(
        int code,
        String message
    ) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getStatusCode() {
        return code;
    }

    @Override
    public String getKrErrorMessage() {
        return message;
    }
}
