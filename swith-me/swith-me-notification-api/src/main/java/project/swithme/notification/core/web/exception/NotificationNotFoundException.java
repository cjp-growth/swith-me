package project.swithme.notification.core.web.exception;

import static project.study.support.codeandmessage.notification.NotificationErrorCodeAndMessage.NOTIFICATION_NOT_FOUND;
import project.study.support.exception.DomainException;

public class NotificationNotFoundException extends DomainException {

    public NotificationNotFoundException() {
        super(NOTIFICATION_NOT_FOUND);
    }
}
