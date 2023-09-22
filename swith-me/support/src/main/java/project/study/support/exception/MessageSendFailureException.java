package project.study.support.exception;

import static project.study.support.codeandmessage.notification.NotificationErrorCodeAndMessage.NOTIFICATION_SEND_FAILURE;

public class MessageSendFailureException extends DomainException {

    public MessageSendFailureException() {
        super(NOTIFICATION_SEND_FAILURE);
    }
}
