package project.study.support.codeandmessage.common;

import lombok.Getter;

@Getter
public enum SuccessCodeAndMessage  {
    OK(
        200,
        "OK"
    ),
    CREATED(
        201,
        "CREATED"
    );

    private final int code;
    private final String statusCode;

    SuccessCodeAndMessage(
        int code,
        String statusCode
    ) {
        this.code = code;
        this.statusCode = statusCode;
    }
}
