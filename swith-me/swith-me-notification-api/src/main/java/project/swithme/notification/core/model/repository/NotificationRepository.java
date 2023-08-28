package project.swithme.notification.core.model.repository;

import org.springframework.data.repository.CrudRepository;
import project.swithme.notification.core.model.document.Notification;

public interface NotificationRepository extends CrudRepository<Notification, String> {

}
