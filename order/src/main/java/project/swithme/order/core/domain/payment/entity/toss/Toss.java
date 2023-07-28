package project.swithme.order.core.domain.payment.entity.toss;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
import project.swithme.order.core.common.BaseInformation;

@Getter
@Entity(name = "toss")
public class Toss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "payment_key")
    private String paymentKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Embedded
    private BaseInformation baseInformation;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 payment 외부 패키지에서 호출하지 말 것.
     */
    protected Toss() {
    }

    public Toss(
        Long userId,
        Long orderId,
        String paymentKey,
        PaymentType paymentType,
        BigDecimal amount
    ) {
        this(null, userId, orderId, paymentKey, paymentType, amount);
    }

    public Toss(
        Long id,
        Long userId,
        Long orderId,
        String paymentKey,
        PaymentType paymentType,
        BigDecimal amount
    ) {
        this.id = id;
        this.orderId = orderId;
        this.paymentKey = paymentKey;
        this.paymentType = paymentType;
        this.amount = amount;
        this.baseInformation = new BaseInformation(userId);
    }

    public Long getId() {
        return id;
    }

    public void register(
        Long orderId,
        Long userId
    ) {
        this.orderId = orderId;
        this.baseInformation = new BaseInformation(userId);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Toss toss)) {
            return false;
        }
        return getId().equals(toss.getId());
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
