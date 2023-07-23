package project.swithme.order.core.web.order.application.service;

import org.springframework.stereotype.Service;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.order.infrastructure.OrderJpaRepository;
import project.swithme.order.core.web.order.application.OrderUseCase;

@Service
public class OrderCreateService implements OrderUseCase {

    private final OrderJpaRepository orderRepository;

    public OrderCreateService(OrderJpaRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long save(Order order) {
        Order newOrder = orderRepository.save(order);
        return newOrder.getId();
    }
}
