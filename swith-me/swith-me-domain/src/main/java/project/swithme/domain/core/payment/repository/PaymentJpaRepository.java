package project.swithme.domain.core.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.swithme.domain.core.payment.entity.Payment;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

}
