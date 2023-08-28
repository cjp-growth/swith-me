package project.swithme.notification.core.web.handler.validator;

import org.springframework.stereotype.Component;

@Component
public class NotificationValidator {

    public void validate(
        Long userId,
        Long orderId,
        Integer eventTypeIndex
    ) {
        if (userId == null || userId <= 0L || userId >= Long.MAX_VALUE) {

        }
    }
}
