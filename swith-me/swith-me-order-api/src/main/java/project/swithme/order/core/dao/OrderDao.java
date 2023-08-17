package project.swithme.order.core.dao;

import java.util.Optional;
import java.util.UUID;
import project.swithme.domain.core.order.entity.Order;

public interface OrderDao {

    Optional<Order> findOrderById(Long id);

    Optional<Order> findOrderById(UUID uniqueId);
}
