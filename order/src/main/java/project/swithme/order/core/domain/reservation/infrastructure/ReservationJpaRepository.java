package project.swithme.order.core.domain.reservation.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import project.swithme.order.core.domain.reservation.entity.Reservation;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {

}
