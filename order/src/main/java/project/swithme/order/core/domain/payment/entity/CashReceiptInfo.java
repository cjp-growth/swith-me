package project.swithme.order.core.domain.payment.entity;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
@Embeddable
public class CashReceiptInfo {

    private String type;
    private String receiptKey;
    private String issueNumber;
    private String receiptUrl;
    private BigDecimal amount;
    private BigDecimal taxFreeAmount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 payment 외부 패키지에서 호출하지 말 것.
     */
    protected CashReceiptInfo() {
    }

    public CashReceiptInfo(
        String type,
        String receiptKey,
        String issueNumber,
        String receiptUrl,
        BigDecimal amount,
        BigDecimal taxFreeAmount
    ) {
        this.type = type;
        this.receiptKey = receiptKey;
        this.issueNumber = issueNumber;
        this.receiptUrl = receiptUrl;
        this.amount = amount;
        this.taxFreeAmount = taxFreeAmount;
    }

    public static CashReceiptInfo createEmptyInfo() {
        return new CashReceiptInfo();
    }
}
