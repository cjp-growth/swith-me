package project.swithme.order.core.domain.payment.repository.toss;

import org.springframework.data.jpa.repository.JpaRepository;
import project.swithme.order.core.domain.payment.entity.Toss;

public interface TossJpaRepository extends JpaRepository<Toss, Long> {

}
