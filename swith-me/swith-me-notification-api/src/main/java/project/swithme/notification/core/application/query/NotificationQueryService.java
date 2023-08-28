package project.swithme.notification.core.application.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.swithme.notification.core.application.NotificationQueryUseCase;
import project.swithme.notification.core.exception.NotificationNotFoundException;
import project.swithme.notification.core.model.document.Notification;
import project.swithme.notification.core.model.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
public class NotificationQueryService implements NotificationQueryUseCase {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification findNotificationById(String orderId) {
        Notification findNotification =
            notificationRepository.findNotificationByOrderUniqueId(orderId)
                .orElseThrow(NotificationNotFoundException::new);

        if (!findNotification.isChecked()) {
            findNotification.updateReadable();
        }
        return findNotification;
    }
}
