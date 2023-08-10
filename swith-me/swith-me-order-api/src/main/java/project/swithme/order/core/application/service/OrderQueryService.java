package project.swithme.order.core.application.service;

import static java.lang.Boolean.FALSE;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.order.repository.OrderJpaRepository;
import project.swithme.order.core.application.OrderQueryUseCase;

@Service
@RequiredArgsConstructor
public class OrderQueryService implements OrderQueryUseCase {

    private final OrderJpaRepository orderRepository;

    @Override
    public Optional<Order> findByUniqueId(String orderUniqueId) {
        return orderRepository.findOrderByUniqueId(UUID.fromString(orderUniqueId), FALSE);
    }
}
