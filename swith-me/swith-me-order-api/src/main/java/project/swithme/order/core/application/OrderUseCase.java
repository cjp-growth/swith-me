package project.swithme.order.core.application;

import project.swithme.domain.core.order.entity.Order;

public interface OrderUseCase {

    Long save(Order order);
}
