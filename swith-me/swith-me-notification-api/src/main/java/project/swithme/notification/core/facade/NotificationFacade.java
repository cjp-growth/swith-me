package project.swithme.notification.core.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.swithme.notification.core.application.NotificationSaveUseCase;
import project.swithme.notification.core.out.OrderQueryPort;

@Component
@RequiredArgsConstructor
public class NotificationFacade {

    private final OrderQueryPort orderQueryPort;
    private final NotificationSaveUseCase notificationSaveUseCase;

    public void sendNotification(
        Long userId,
        String orderUniqueId,
        String messageTemplate
    ) {
        String title = orderQueryPort.findOrderTitleByUniqueId(orderUniqueId);
        String message = String.format(messageTemplate, title);
        notificationSaveUseCase.save(userId, orderUniqueId, message);
    }
}
