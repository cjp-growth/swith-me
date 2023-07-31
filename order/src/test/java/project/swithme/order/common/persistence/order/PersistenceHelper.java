package project.swithme.order.common.persistence.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.order.infrastructure.OrderJpaRepository;

@Component
public class PersistenceHelper {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order persist(Order entity) {
        return orderJpaRepository.save(entity);
    }
}
