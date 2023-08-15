package project.swithme.order.core.application;

import java.util.Optional;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.domain.core.order.entity.Order;

public interface OrderQueryUseCase {

    Order findOrderById(StudyWithMeUser studyWithMeUser, Long orderId);

    Optional<Order> findByUniqueId(String orderUniqueId);
}
