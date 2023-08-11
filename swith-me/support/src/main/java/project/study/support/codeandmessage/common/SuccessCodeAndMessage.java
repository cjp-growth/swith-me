package project.study.support.codeandmessage.common;

import project.study.support.codeandmessage.CodeAndMessage;

public enum SuccessCodeAndMessage implements CodeAndMessage {
    OK(200, "Success"),
    CREATED(201, "Created");

    private final int code;
    private final String message;

    SuccessCodeAndMessage(
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
