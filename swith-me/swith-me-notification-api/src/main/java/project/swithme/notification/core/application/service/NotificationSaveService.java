package project.swithme.notification.core.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.swithme.notification.core.application.NotificationSaveUseCase;
import project.swithme.notification.core.model.document.Notification;
import project.swithme.notification.core.model.repository.NotificationRepository;

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
