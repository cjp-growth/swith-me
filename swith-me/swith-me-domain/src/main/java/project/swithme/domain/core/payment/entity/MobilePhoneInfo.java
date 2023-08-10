package project.swithme.domain.core.payment.entity;

import static project.swithme.domain.core.payment.entity.SettlementStatus.findByStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
@Embeddable
public class MobilePhoneInfo {

    @Column(name = "customer_mobile_phone")
    private String customerMobilePhone;

    @Column(name = "mobile_settlement_status")
    @Enumerated(EnumType.STRING)
    private SettlementStatus mobileSettlementStatus;

    @Column(name = "mobile_receipt_url")
    private String mobileReceiptUrl;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 payment 외부 패키지에서 호출하지 말 것.
     */
    protected MobilePhoneInfo() {
    }

    public MobilePhoneInfo(
        String customerMobilePhone,
        String mobileSettlementStatus,
        String mobileReceiptUrl
    ) {
        this.customerMobilePhone = customerMobilePhone;
        this.mobileSettlementStatus = findByStatus(mobileSettlementStatus);
        this.mobileReceiptUrl = mobileReceiptUrl;
    }

    public static MobilePhoneInfo createEmptyInfo() {
        return new MobilePhoneInfo();
    }
}
