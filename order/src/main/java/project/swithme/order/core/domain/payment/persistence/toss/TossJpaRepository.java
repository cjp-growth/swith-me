package project.swithme.order.core.domain.payment.persistence.toss;

import org.springframework.data.jpa.repository.JpaRepository;
import project.swithme.order.core.domain.payment.entity.toss.Toss;

public interface TossJpaRepository extends JpaRepository<Toss, Long> {

}
