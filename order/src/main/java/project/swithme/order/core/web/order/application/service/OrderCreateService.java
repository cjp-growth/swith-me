package project.swithme.order.core.web.order.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.order.repository.OrderJpaRepository;
import project.swithme.order.core.web.order.application.OrderUseCase;

@Service
@RequiredArgsConstructor
public class OrderCreateService implements OrderUseCase {

    private final OrderJpaRepository orderRepository;

    @Override
    public Long save(Order order) {
        Order newOrder = orderRepository.save(order);
        return newOrder.getId();
    }
}
