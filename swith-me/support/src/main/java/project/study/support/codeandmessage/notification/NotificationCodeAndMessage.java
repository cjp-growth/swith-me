package project.study.support.codeandmessage.notification;

import project.study.support.codeandmessage.CodeAndMessage;

public enum NotificationCodeAndMessage implements CodeAndMessage {
    NOTIFICATION_SEND_FAILURE(500, "메시지 전송에 실패했습니다.");

    private final int code;
    private final String message;

    NotificationCodeAndMessage(
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
