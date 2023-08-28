package project.swithme.notification.test.integrationtest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static project.swithme.notification.common.message.PaymentMessageTemplate.PAYMENT_SUCCESS;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import project.swithme.notification.core.domain.notification.document.Notification;
import project.swithme.notification.core.web.application.NotificationQueryUseCase;
import project.swithme.notification.core.web.application.NotificationSaveUseCase;
import project.swithme.notification.core.web.exception.NotificationNotFoundException;
import project.swithme.notification.core.web.facade.NotificationFacade;
import project.swithme.notification.core.web.out.OrderQueryPort;
import project.swithme.notification.test.IntegrationTestBase;

@DisplayName("[IntegrationTest] 알림 저장 통합 테스트")
class NotificationSaveIntegrationTest extends IntegrationTestBase {

    private NotificationFacade notificationFacade;

    @SpyBean
    private OrderQueryPort orderQueryPort;

    @SpyBean
    private NotificationSaveUseCase notificationSaveUseCase;

    @Autowired
    private NotificationQueryUseCase notificationQueryUseCase;

    @BeforeEach
    void setUp() {
        notificationFacade = new NotificationFacade(
            orderQueryPort,
            notificationSaveUseCase
        );
    }

    @Test
    @DisplayName("알림이 저장되면 이를 조회할 수 있다.")
    void notification_save_test() {
        String title = "독서실 1달 정기 이용권이 결제되었습니다.";
        UUID orderUniqueId = UUID.randomUUID();

        doReturn(title)
            .when(orderQueryPort)
            .findOrderTitleByUniqueId(orderUniqueId.toString());

        notificationFacade.sendNotification(
            1L,
            orderUniqueId.toString(),
            PAYMENT_SUCCESS.getMessage()
        );

        Notification notification =
            notificationQueryUseCase.findNotificationById(orderUniqueId.toString());

        assertNotNull(notification);
    }

    @Test
    @DisplayName("알림이 존재하지 않으면 NotificationNotFoundException이 발생한다.")
    void notification_not_found_test() {
        String title = "독서실 1달 정기 이용권이 결제되었습니다.";
        UUID orderUniqueId = UUID.randomUUID();
        UUID invalidOrderUniqueId = UUID.randomUUID();

        doReturn(title)
            .when(orderQueryPort)
            .findOrderTitleByUniqueId(orderUniqueId.toString());

        notificationFacade.sendNotification(
            1L,
            orderUniqueId.toString(),
            PAYMENT_SUCCESS.getMessage()
        );

        assertThatThrownBy(() ->
            notificationQueryUseCase.findNotificationById(invalidOrderUniqueId.toString())
        ).isExactlyInstanceOf(NotificationNotFoundException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("알림을 찾을 수 없습니다.");
    }
}
