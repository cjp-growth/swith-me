package project.swithme.order.core.domain.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import project.swithme.order.core.common.BaseEntity;
import project.swithme.order.core.common.BaseInformation;

import java.time.Instant;
import java.util.Objects;

@Entity(name = "order")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "order_status",
            columnDefinition = "ENUM('PAYMENT_REQUEST', 'COMPLETE', 'CANCEL', 'REFUND')"
    )
    private OrderStatus orderStatus;

    @Column(name = "deposit_deadline")
    private Instant depositDeadline;

    @Column(name = "refund_reason")
    private String refundReason;

    @Embedded
    private BaseInformation baseInformation;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 order 외부 패키지에서 호출하지 말 것.
     */
    protected Order() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return getId().equals(order.getId());
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

