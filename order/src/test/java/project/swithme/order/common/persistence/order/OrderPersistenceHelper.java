package project.swithme.order.common.persistence.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.swithme.order.common.persistence.PersistenceHelper;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.order.infrastructure.OrderJpaRepository;

@Component
public class OrderPersistenceHelper implements PersistenceHelper<Order> {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Override
    public Order persist(Order entity) {
        return orderJpaRepository.save(entity);
    }

    @Override
    public Order findById(Order entity) {
        return orderJpaRepository.findOrderByUniqueId(entity.getUniqueId(), false).get();
    }
}
