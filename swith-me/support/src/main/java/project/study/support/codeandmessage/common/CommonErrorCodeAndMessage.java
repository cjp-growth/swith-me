package project.study.support.codeandmessage.common;

import project.study.support.codeandmessage.CodeAndMessage;

public enum CommonErrorCodeAndMessage implements CodeAndMessage {
    INVALID_CLIENT_REQUEST(
        400,
        "INVALID_CLIENT_REQUEST",
        "올바르지 않은 요청입니다.",
        "Invalid client request."
    ),
    API_SPEC_UN_MATCHED(
        400,
        "API_SPEC_UN_MATCHED",
        "외부 서버 API 스펙과 매핑을 확인해주세요.",
        "Please check the external server API specification and mapping."
    ),
    INVALID_PATH_VARIABLE(
        400,
        "INVALID_PATH_VARIABLE",
        "올바른 PATH 경로를 확인해주세요.",
        "Please check the correct path path."
    ),
    INVALID_QUERY_PARAMETERS(
        400,
        "INVALID_QUERY_PARAMETERS",
        "올바른 파라미터를 입력해주세요.",
        "Please enter the correct parameters."
    ),
    UN_AUTHORIZED(
        401,
        "UN_AUTHORIZED",
        "권한이 존재하지 않습니다.",
        "Permission does not exist."
    ),
    URL_NOTFOUND(
        404,
        "URL_NOTFOUND",
        "존재하지 않는 URL 입니다.",
        "URL does not exist."
    ),
    INTERNAL_SERVER_ERROR(500,
        "INTERNAL_SERVER_ERROR",
        "서버 내부 오류입니다.",
        "Internal server error."
    ),
    BAD_GATEWAY(502,
        "BAD_GATEWAY",
        "외부 서버와 통신하는 과정에서 오류가 발생했습니다.",
        "An error occurred while communicating with the external server."
    );

    private final int code;
    private final String errorCode;
    private final String krErrorMessage;
    private final String engErrorMessage;

    CommonErrorCodeAndMessage(
        int code,
        String errorCode,
        String krErrorMessage,
        String engErrorMessage
    ) {
        this.code = code;
        this.errorCode = errorCode;
        this.krErrorMessage = krErrorMessage;
        this.engErrorMessage = engErrorMessage;
    }

    @Override
    public int getStatusCode() {
        return code;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getKrErrorMessage() {
        return krErrorMessage;
    }

    @Override
    public String getEnErrorMessage() {
        return engErrorMessage;
    }
}
