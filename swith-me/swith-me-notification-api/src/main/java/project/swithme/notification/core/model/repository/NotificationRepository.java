package project.swithme.notification.core.model.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import project.swithme.notification.core.model.document.Notification;

public interface NotificationRepository extends CrudRepository<Notification, String> {

    @Query("{'orderId' : ?0}")
    Optional<Notification> findNotificationByOrderUniqueId(String orderId);
}
