package project.swithme.notification.test.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static project.swithme.notification.common.fixture.NotificationFixture.createNotification;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.notification.core.domain.notification.document.Notification;

@DisplayName("[UnitTest] 알림 단위 테스트")
class NotificationUnitTest {

    @Test
    @DisplayName("올바른 값이 입력되면 알림 객체를 생성할 수 있다.")
    void notification_create_test() {
        Notification notification = new Notification(
            1L,
            UUID.randomUUID().toString(),
            "독서실 1달 정기 이용권이 결제되었습니다."
        );

        assertNotNull(notification);
        assertNotNull(createNotification(UUID.randomUUID(), Instant.now()));
    }

    @Test
    @DisplayName("알림을 읽었다면 상태가 바뀐다.")
    void notification_read_update_test() {
        Notification notification = new Notification(
            1L,
            UUID.randomUUID().toString(),
            "독서실 1달 정기 이용권이 결제되었습니다."
        );

        notification.updateReadable();

        assertEquals(true, notification.isChecked());
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void equals_test() {
        Notification notification = createNotification(
            UUID.randomUUID(),
            Instant.now()
        );

        Notification otherNotification = createNotification(
            UUID.randomUUID(),
            Instant.now()
        );

        assertTrue(notification.equals(notification));
        assertTrue(!"1".equals(otherNotification));
        assertTrue(!notification.equals(otherNotification));
    }

    @Test
    @DisplayName("hashcode를 재정의 하면 값으로 객체를 비교한다.")
    void hashcode_test() {
        Notification notification = createNotification(
            UUID.randomUUID(),
            Instant.now()
        );
        Notification otherNotification = createNotification(
            UUID.randomUUID(),
            Instant.now()
        );

        assertTrue(notification.hashCode() == notification.hashCode());
        assertFalse(notification.hashCode() == otherNotification.hashCode());
    }

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        Notification notification = createNotification(
            UUID.randomUUID(),
            Instant.now()
        );
        String expected = String.format("id: %s", notification.get_id());

        assertEquals(expected, notification.toString());
    }
}
