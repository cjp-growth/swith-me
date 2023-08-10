package project.swithme.order.common.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.order.repository.OrderJpaRepository;

@Component
public class PersistenceHelper {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    public Order persist(Order order) {
        return orderJpaRepository.save(order);
    }
}
