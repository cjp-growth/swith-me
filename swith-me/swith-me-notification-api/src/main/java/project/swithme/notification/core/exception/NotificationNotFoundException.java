package project.swithme.notification.core.exception;

import static project.study.support.codeandmessage.notification.NotificationCodeAndMessage.NOTIFICATION_NOT_FOUND;
import project.study.support.exception.DomainException;

public class NotificationNotFoundException extends DomainException {

    public NotificationNotFoundException() {
        super(NOTIFICATION_NOT_FOUND);
    }
}
