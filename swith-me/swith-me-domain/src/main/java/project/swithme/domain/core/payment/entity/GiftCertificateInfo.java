package project.swithme.domain.core.payment.entity;

import static project.swithme.domain.core.payment.entity.SettlementStatus.findByStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
@Embeddable
public class GiftCertificateInfo {

    @Column(name = "gift_approve_no")
    private String giftApproveNo;

    @Column(name = "gift_settlement_status")
    @Enumerated(EnumType.STRING)
    private SettlementStatus giftSettlementStatus;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 payment 외부 패키지에서 호출하지 말 것.
     */
    protected GiftCertificateInfo() {
    }

    public GiftCertificateInfo(
        String giftApproveNo,
        String giftSettlementStatus
    ) {
        this.giftApproveNo = giftApproveNo;
        this.giftSettlementStatus = findByStatus(giftSettlementStatus);
    }

    public static GiftCertificateInfo createEmptyInfo() {
        return new GiftCertificateInfo();
    }
}
