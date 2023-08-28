package project.swithme.notification.common.fixture;

import java.time.Instant;
import java.util.UUID;
import project.swithme.notification.common.utils.UUIDGenerators;
import project.swithme.notification.core.model.document.EventType;
import project.swithme.notification.core.model.document.Notification;

public final class NotificationFixture {

    public static final UUID NOTIFICATION_UNIQUE_ID =
        UUIDGenerators.createUUID();

    public static final UUID ORDER_UNIQUE_ID =
        UUIDGenerators.createUUID();

    public static final Long USER_ID = 1L;

    public static final String ORDER_TITLE = "스터디 카페 1달 정기 이용권 및 락커 이용권 결제가 성공적으로 이루어졌습니다.";

    private NotificationFixture() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public static Notification createNotification(
        UUID _id,
        Instant createdAt
    ) {
        return new Notification(
            _id.toString(),
            NOTIFICATION_UNIQUE_ID,
            USER_ID,
            ORDER_UNIQUE_ID.toString(),
            EventType.ORDER,
            ORDER_TITLE,
            false,
            createdAt
        );
    }
}
