package project.swithme.order.core.domain.payment.entity;

import static project.swithme.order.core.domain.payment.entity.CardType.findByType;
import static project.swithme.order.core.domain.payment.entity.InterestPayer.findByPayer;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
@Embeddable
public class CardInfo {

    @Column(name = "card_amount")
    private BigDecimal cardAmount;

    @Column(name = "card_issuer_company")
    @Enumerated(EnumType.STRING)
    private CardIssuerCompany cardIssuerCompany;

    @Column(name = "card_acquirer_comany")
    @Enumerated(EnumType.STRING)
    private CardAcquirerComany cardAcquirerComany;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "installment_plan_months")
    private int installmentPlanMonths;

    @Column(name = "card_approve_no")
    private String cardApproveNo;

    @Column(name = "use_card_point")
    private boolean useCardPoint;

    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name = "owner_type")
    @Enumerated(EnumType.STRING)
    private OwnerType ownerType;

    @Column(name = "acquire_status")
    @Enumerated(EnumType.STRING)
    private AcquireStatus acquireStatus;

    @Column(name = "is_interest_free")
    private boolean isInterestFree;

    @Column(name = "interest_payer")
    @Enumerated(EnumType.STRING)
    private InterestPayer interestPayer;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 payment 외부 패키지에서 호출하지 말 것.
     */
    protected CardInfo() {
    }

    public CardInfo(
        BigDecimal cardAmount,
        String issuerCode,
        String acquirerCode,
        String cardNumber,
        String installmentPlanMonths,
        String cardApproveNo,
        Boolean useCardPoint,
        String cardType,
        String ownerType,
        String acquireStatus,
        Boolean isInterestFree,
        String interestPayer
    ) {
        this.cardAmount = cardAmount;
        this.cardIssuerCompany = CardIssuerCompany.findByCode(issuerCode);
        this.cardAcquirerComany = CardAcquirerComany.findByCode(acquirerCode);
        this.cardNumber = cardNumber;
        this.installmentPlanMonths = Integer.parseInt(installmentPlanMonths);
        this.isInterestFree = isInterestFree;
        this.interestPayer = findByPayer(interestPayer);
        this.cardApproveNo = cardApproveNo;
        this.useCardPoint = useCardPoint;
        this.cardType = findByType(cardType);
        this.ownerType = OwnerType.findByType(ownerType);
        this.acquireStatus = AcquireStatus.findByStatus(acquireStatus);
    }
}
