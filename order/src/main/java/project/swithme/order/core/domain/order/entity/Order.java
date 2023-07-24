package project.swithme.order.core.domain.order.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import project.swithme.order.core.common.BaseEntity;
import project.swithme.order.core.common.BaseInformation;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.time.Duration.ofHours;

@Getter
@Entity(name = "`order`")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "reservation_id")
    private Long reservationId;

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

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST})
    private List<OrderLine> orderLines = new ArrayList<>();

    @Embedded
    private BaseInformation baseInformation;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 order 외부 패키지에서 호출하지 말 것.
     */
    protected Order() {
    }

    public Order(
            Long userId,
            List<OrderLine> orderLines
    ) {
        this.userId = userId;
        this.orderStatus = OrderStatus.PAYMENT_REQUEST;
        this.depositDeadline = createDeadline();
        this.orderLines = init(orderLines);
        this.baseInformation = new BaseInformation(userId);
    }

    private List<OrderLine> init(List<OrderLine> orderLines) {
        orderLines.forEach(
                orderLine -> orderLine.add(this)
        );
        return orderLines;
    }

    private Instant createDeadline() {
        Instant now = Instant.now();
        return now.plus(ofHours(1));
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

