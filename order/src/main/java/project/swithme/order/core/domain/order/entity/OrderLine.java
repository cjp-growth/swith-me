package project.swithme.order.core.domain.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
import project.swithme.order.core.common.BaseEntity;
import project.swithme.order.core.common.BaseInformation;

@Getter
@Entity(name = "order_line")
public class OrderLine extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "study_cafe_id")
    private Long studyCafeId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Embedded
    private BaseInformation baseInformation;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 order 외부 패키지에서 호출하지 말 것.
     */
    protected OrderLine() {
    }

    private OrderLine(
        Long studyCafeId,
        Long productId,
        BigDecimal price,
        BaseInformation baseInformation
    ) {
        this.studyCafeId = studyCafeId;
        this.productId = productId;
        this.price = price;
        this.baseInformation = baseInformation;
    }

    public OrderLine(
        Long id,
        Long studyCafeId,
        Long productId,
        BigDecimal price,
        Order order,
        BaseInformation baseInformation
    ) {
        this.id = id;
        this.studyCafeId = studyCafeId;
        this.productId = productId;
        this.price = price;
        this.order = order;
        this.baseInformation = baseInformation;
    }

    public static OrderLine createOrderLine(
        Long userId,
        Long studyCafeId,
        Long productId,
        BigDecimal price
    ) {
        return new OrderLine(
            studyCafeId,
            productId,
            price,
            new BaseInformation(userId)
        );
    }

    public void add(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof OrderLine orderLine)) {
            return false;
        }
        return getId().equals(orderLine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.format("orderLineId: %s", id);
    }
}

