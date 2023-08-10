package project.swithme.domain.core.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import project.swithme.domain.core.history.entity.OrderPaymentHistory;

public interface OrderPaymentHistoryJpaRepository extends
    JpaRepository<OrderPaymentHistory, Long>, RevisionRepository<OrderPaymentHistory, Long, Long> {

}
