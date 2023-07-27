package project.swithme.order.core.domain.order.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import project.swithme.order.core.domain.order.entity.Order;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
