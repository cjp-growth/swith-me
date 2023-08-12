package project.swithme.domain.core.order.entity;

import static java.time.Duration.ofHours;
import com.fasterxml.uuid.Generators;
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
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import org.hibernate.envers.Audited;
import project.swithme.domain.core.common.BaseEntity;
import project.swithme.domain.core.common.BaseInformation;

@Getter
@Audited
@Entity(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "unique_id", columnDefinition = "uuid", length = 16)
    private UUID uniqueId;

    @Column(name = "title")
    private String title;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "discounted_total_price")
    private BigDecimal discountedTotalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_type")
    private PayType payType;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
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
        String title,
        PayType payType,
        List<OrderLine> orderLines
    ) {
        this.userId = userId;
        this.title = title;
        this.payType = payType;
        this.uniqueId = Generators.timeBasedGenerator().generate();
        this.orderStatus = OrderStatus.PAYMENT_REQUEST;
        this.depositDeadline = createDeadline();
        this.orderLines = init(orderLines);
        this.totalPrice = getTotalPrice();
        this.discountedTotalPrice = totalPrice;
        this.baseInformation = new BaseInformation(userId);
    }

    public Order(
        Long id,
        Long userId,
        Long reservationId,
        UUID uniqueId,
        String title,
        PayType payType,
        OrderStatus orderStatus,
        Instant depositDeadline,
        String refundReason,
        List<OrderLine> orderLines,
        BaseInformation baseInformation
    ) {
        this.id = id;
        this.userId = userId;
        this.reservationId = reservationId;
        this.uniqueId = uniqueId;
        this.title = title;
        this.payType = payType;
        this.orderStatus = orderStatus;
        this.depositDeadline = depositDeadline;
        this.refundReason = refundReason;
        this.orderLines = init(orderLines);
        this.totalPrice = getTotalPrice();
        this.discountedTotalPrice = totalPrice;
        this.baseInformation = baseInformation;
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

    public boolean isValidStatus() {
        return this.orderStatus.equals(OrderStatus.PAYMENT_REQUEST);
    }

    public boolean validatePrice(BigDecimal amount) {
        return getTotalPrice().compareTo(amount) == 0;
    }

    public BigDecimal getTotalPrice() {
        return orderLines.stream()
            .map(OrderLine::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void updateOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void updatePrice(BigDecimal discountedAmount) {
        if (discountedAmount != null) {
            this.discountedTotalPrice = discountedAmount;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Order order)) {
            return false;
        }
        return getId().equals(order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.format("orderId: %s", id);
    }
}

