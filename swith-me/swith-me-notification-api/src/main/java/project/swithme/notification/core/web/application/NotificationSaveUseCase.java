package project.swithme.notification.core.web.application;

import project.swithme.notification.core.domain.notification.document.Notification;

public interface NotificationSaveUseCase {

    Notification save(Long userId, String orderUniqueId, String message);
}
