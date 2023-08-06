package project.swithme.order.core.domain.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import project.swithme.order.core.domain.history.entity.OrderPaymentHistory;

public interface OrderPaymentHistoryJpaRepository extends
    JpaRepository<OrderPaymentHistory, Long>, RevisionRepository<OrderPaymentHistory, Long, Long> {

}
