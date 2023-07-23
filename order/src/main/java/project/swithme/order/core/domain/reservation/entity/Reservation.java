package project.swithme.order.core.domain.reservation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import project.swithme.order.core.common.BaseEntity;
import project.swithme.order.core.common.BaseInformation;

import java.time.Instant;
import java.util.Objects;

@Getter
@Entity(name = "reservation")
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "study_cafe_id")
    private Long studyCafeId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "reservation_status",
            columnDefinition = "ENUM('RESERVED', 'CANCEL')"
    )
    private ReservationStatus reservationStatus;

    @Column(name = "cancel_reason")
    private String cancelReason;

    @Embedded
    private BaseInformation baseInformation;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 reservation 외부 패키지에서 호출하지 말 것.
     */
    protected Reservation() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
