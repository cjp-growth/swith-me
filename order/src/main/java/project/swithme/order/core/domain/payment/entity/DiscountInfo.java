package project.swithme.order.core.domain.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;

@Getter
@Embeddable
public class DiscountInfo {

    @Column(name = "discounted_amount")
    private BigDecimal discountedAmount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 payment 외부 패키지에서 호출하지 말 것.
     */
    protected DiscountInfo() {
    }

    public DiscountInfo(BigDecimal discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public static DiscountInfo createEmptyInfo() {
        return new DiscountInfo();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DiscountInfo that)) {
            return false;
        }
        return getDiscountedAmount().equals(that.getDiscountedAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDiscountedAmount());
    }

    @Override
    public String toString() {
        return discountedAmount.toPlainString();
    }
}
