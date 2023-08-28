package project.swithme.notification.core.web.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.swithme.notification.core.web.application.NotificationSaveUseCase;
import project.swithme.notification.core.web.out.OrderQueryPort;

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
