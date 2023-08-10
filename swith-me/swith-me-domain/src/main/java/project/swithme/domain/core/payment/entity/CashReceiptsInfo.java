package project.swithme.domain.core.payment.entity;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
@Embeddable
public class CashReceiptsInfo {

    private String receiptKey;
    private String orderId;
    private String orderName;
    private String type;
    private String issueNumber;
    private String receiptUrl;
    private String businessNumber;
    private String transactionType;
    private BigDecimal amount;
    private BigDecimal taxFreeAmount;
    private String issueStatus;
    private String customerIdentityNumber;
    private String requestedAt;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 payment 외부 패키지에서 호출하지 말 것.
     */
    protected CashReceiptsInfo() {
    }

    public static CashReceiptsInfo createEmptyInfo() {
        return new CashReceiptsInfo();
    }
}
