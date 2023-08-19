package project.swithme.order.core.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import project.swithme.domain.common.StudyWithMeUser;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.order.common.utils.Cursor;

public interface OrderDao {

    Optional<Order> findOrderById(Long id);

    Optional<Order> findOrderById(UUID uniqueId);

    List<Order> findMyOrders(StudyWithMeUser studyWithMeUser, Cursor cursor);
}
