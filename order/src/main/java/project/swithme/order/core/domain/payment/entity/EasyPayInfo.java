package project.swithme.order.core.domain.payment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
@Embeddable
public class EasyPayInfo {

    @Column(name = "provider")
    private String provider;

    @Column(name = "easy_pay_amount")
    private BigDecimal easyPayAmount;

    @Column(name = "easy_pay_discounted_amount")
    private BigDecimal easyPayDiscountedAmount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 payment 외부 패키지에서 호출하지 말 것.
     */
    protected EasyPayInfo() {
    }

    public EasyPayInfo(
        String provider,
        BigDecimal easyPayAmount,
        BigDecimal easyPayDiscountedAmount
    ) {
        this.provider = provider;
        this.easyPayAmount = easyPayAmount;
        this.easyPayDiscountedAmount = easyPayDiscountedAmount;
    }

    public static EasyPayInfo createEmptyInfo() {
        return new EasyPayInfo();
    }
}
