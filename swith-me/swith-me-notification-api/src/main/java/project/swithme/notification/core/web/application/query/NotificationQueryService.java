package project.swithme.notification.core.web.application.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.swithme.notification.core.domain.notification.document.Notification;
import project.swithme.notification.core.domain.notification.repository.NotificationRepository;
import project.swithme.notification.core.web.application.NotificationQueryUseCase;
import project.swithme.notification.core.web.exception.NotificationNotFoundException;

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
