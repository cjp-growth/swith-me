package project.swithme.order.core.web.order.application.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.order.repository.OrderJpaRepository;
import project.swithme.order.core.web.order.application.OrderQueryUseCase;

@Service
@RequiredArgsConstructor
public class OrderQueryService implements OrderQueryUseCase {

    private final OrderJpaRepository orderRepository;

    @Override
    public Optional<Order> findByUniqueId(String orderUniqueId) {
        return orderRepository.findOrderByUniqueId(UUID.fromString(orderUniqueId), false);
    }
}
