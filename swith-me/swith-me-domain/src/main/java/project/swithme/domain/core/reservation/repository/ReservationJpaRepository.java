package project.swithme.domain.core.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.swithme.domain.core.reservation.entity.Reservation;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {

}
