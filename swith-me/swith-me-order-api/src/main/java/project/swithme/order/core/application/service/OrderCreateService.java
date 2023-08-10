package project.swithme.order.core.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.order.repository.OrderJpaRepository;
import project.swithme.order.core.application.OrderUseCase;

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
