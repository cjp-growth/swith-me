package project.swithme.domain.common.sut.payment;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import project.swithme.domain.core.payment.entity.CardInfo;

public final class CardInfoSut {

    private final CardInfo cardInfo;

    private CardInfoSut() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public CardInfoSut(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public CardInfoSut shouldExist() {
        assertNotNull(cardInfo);
        return this;
    }


    public CardInfoSut withCardAmount() {
        assertNotNull(cardInfo.getCardAmount());
        return this;
    }

    public CardInfoSut withIssuerCompany() {
        assertNotNull(cardInfo.getCardIssuerCompany());
        return this;
    }

    public CardInfoSut withAcquirerCompany() {
        assertNotNull(cardInfo.getCardAcquirerCompany());
        return this;
    }

    public CardInfoSut withCardNumber() {
        assertNotNull(cardInfo.getCardNumber());
        return this;
    }

    public CardInfoSut withInstallmentPlanMonths() {
        assertNotNull(cardInfo.getInstallmentPlanMonths());
        return this;
    }

    public CardInfoSut withCardApproveNo() {
        assertNotNull(cardInfo.getCardApproveNo());
        return this;
    }

    public CardInfoSut withUseCardPoint() {
        assertNotNull(cardInfo.getUseCardPoint());
        return this;
    }

    public CardInfoSut withCardType() {
        assertNotNull(cardInfo.getCardType());
        return this;
    }

    public CardInfoSut withOwnerType() {
        assertNotNull(cardInfo.getOwnerType());
        return this;
    }

    public CardInfoSut withAcquireStatus() {
        assertNotNull(cardInfo.getAcquireStatus());
        return this;
    }

    public CardInfoSut withIsInterestFree() {
        assertNotNull(cardInfo.getIsInterestFree());
        return this;
    }

    public CardInfoSut withInterestPayer() {
        assertNotNull(cardInfo.getInterestPayer());
        return this;
    }
}
