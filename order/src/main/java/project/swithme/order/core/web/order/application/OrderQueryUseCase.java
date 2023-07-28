package project.swithme.order.core.web.order.application;

import java.util.Optional;
import project.swithme.order.core.domain.order.entity.Order;

public interface OrderQueryUseCase {

    Optional<Order> findOrderByUniqueId(String orderUniqueId);
}
