package project.study.support.codeandmessage.notification;

import project.study.support.codeandmessage.CodeAndMessage;

public enum NotificationErrorCodeAndMessage implements CodeAndMessage {
    NOTIFICATION_NOT_FOUND(
        404,
        "알림을 찾을 수 없습니다.",
        "Can not find notification."
    ),
    NOTIFICATION_SEND_FAILURE(
        700,
        "메시지 전송에 실패했습니다.",
        "Message sending failed."
    );

    private final int code;
    private final String krErrorMessage;
    private final String engErrorMessage;

    NotificationErrorCodeAndMessage(
        int code,
        String krErrorMessage,
        String engErrorMessage
    ) {
        this.code = code;
        this.krErrorMessage = krErrorMessage;
        this.engErrorMessage = engErrorMessage;
    }

    @Override
    public String getErrorCode() {
        return name();
    }

    @Override
    public int getStatusCode() {
        return code;
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
