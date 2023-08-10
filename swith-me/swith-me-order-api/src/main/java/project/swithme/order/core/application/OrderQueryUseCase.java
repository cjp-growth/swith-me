package project.swithme.order.core.application;

import java.util.Optional;
import project.swithme.domain.core.order.entity.Order;

public interface OrderQueryUseCase {

    Optional<Order> findByUniqueId(String orderUniqueId);
}
