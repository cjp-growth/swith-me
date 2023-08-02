package project.swithme.order.core.domain.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.swithme.order.core.domain.payment.entity.Payment;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

}
