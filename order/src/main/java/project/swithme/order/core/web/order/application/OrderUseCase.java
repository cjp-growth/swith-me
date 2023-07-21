package project.swithme.order.core.web.order.application;

import project.swithme.order.core.domain.order.entity.Order;

public interface OrderUseCase {

    Long save(Order order);
}
