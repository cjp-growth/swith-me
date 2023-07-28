package project.swithme.order.core.domain.order.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.swithme.order.core.domain.order.entity.Order;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM orders o JOIN o.orderLines WHERE o.uniqueId = :uniqueId AND o.baseInformation.deleted = :deleted")
    Optional<Order> findOrderByUniqueId(
        @Param("uniqueId") UUID uniqueId,
        @Param("deleted") Boolean deleted
    );
}
