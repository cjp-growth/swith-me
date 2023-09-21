package project.swithme.order.common.persistence;

import static order.OrderFixture.createOrder;
import static project.swithme.domain.core.order.entity.OrderStatus.PAYMENT_REQUEST;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.order.repository.OrderJpaRepository;

@Component
public class PersistenceHelper {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order persist(Order order) {
        return orderJpaRepository.save(order);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Order> persistAll() {
        List<Order> orders = new ArrayList<>();
        for (int index = 1; index <= 100; index++) {
            orders.add(createOrder(PAYMENT_REQUEST));
        }
        orderJpaRepository.saveAllAndFlush(orders);
        return orders;
    }
}
