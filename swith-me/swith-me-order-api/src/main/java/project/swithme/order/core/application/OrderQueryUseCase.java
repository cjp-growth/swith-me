package project.swithme.order.core.application;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.order.common.utils.Cursor;

public interface OrderQueryUseCase {

    Order findOrderById(StudyWithMeUser studyWithMeUser, Long orderId);

    Optional<Order> findByUniqueId(String orderUniqueId);

    List<Order> findMyOrders(
        StudyWithMeUser studyWithMeUser,
        Cursor cursor,
        LocalDate startDate,
        LocalDate endDate
    );
}
