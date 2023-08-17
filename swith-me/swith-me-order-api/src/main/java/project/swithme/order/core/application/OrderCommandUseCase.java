package project.swithme.order.core.application;

import project.swithme.domain.core.order.entity.Order;

public interface OrderCommandUseCase {

    Long save(Order order);
}
