package project.swithme.notification.core.application;

import project.swithme.notification.core.model.document.Notification;

public interface NotificationSaveUseCase {

    Notification save(Long userId, String orderUniqueId, String message);
}
