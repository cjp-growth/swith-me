package project.swithme.notification.core.web.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.swithme.notification.core.domain.notification.document.Notification;
import project.swithme.notification.core.domain.notification.repository.NotificationRepository;
import project.swithme.notification.core.web.application.NotificationSaveUseCase;

@Service
@RequiredArgsConstructor
public class NotificationSaveService implements NotificationSaveUseCase {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification save(
        Long userId,
        String orderUniqueId,
        String message
    ) {
        Notification newNotification = new Notification(userId, orderUniqueId, message);
        return notificationRepository.save(newNotification);
    }
}
