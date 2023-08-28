package project.swithme.notification.core.application;

import project.swithme.notification.core.model.document.Notification;

public interface NotificationQueryUseCase {

    Notification findNotificationById(String orderId);
}
