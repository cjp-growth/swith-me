package project.swithme.domain.test.payment;

import static order.PaymentFixture.createCardInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.swithme.domain.common.sut.payment.CardInfoSut;
import project.swithme.domain.core.payment.entity.CardInfo;

@DisplayName("[UnitTest] 카드 정보 단위 테스트")
class CardInfoUnitTest {

    @Test
    @DisplayName("카드 정보 객체를 생성할 수 있다.")
    void card_info_create_test() {
        CardInfoSut sut = new CardInfoSut(createCardInfo());

        sut.shouldExist()
            .withCardAmount()
            .withIssuerCompany()
            .withAcquirerCompany()
            .withCardNumber()
            .withInstallmentPlanMonths()
            .withCardApproveNo()
            .withUseCardPoint()
            .withCardType()
            .withOwnerType()
            .withAcquireStatus()
            .withIsInterestFree()
            .withInterestPayer();
    }

    @Test
    @DisplayName("equals를 재정의 하면 값으로 객체를 비교한다.")
    void equals_test() {
        CardInfo cardInfo = createCardInfo();
        CardInfo sameCardInfo = createCardInfo();
        CardInfo otherCardInfo = new CardInfo(
            new BigDecimal(130_000L),
            "24",
            "24",
            "14345*123409*01234",
            "0",
            "000000",
            Boolean.FALSE,
            "신용",
            "개인",
            "COMPLETED",
            Boolean.FALSE,
            "BUYER"
        );

        CardInfo otherCardInfoB = new CardInfo(
            new BigDecimal(130_000L),
            "21",
            "24",
            "14345*123409*01234",
            "0",
            "000000",
            Boolean.FALSE,
            "신용",
            "개인",
            "COMPLETED",
            Boolean.FALSE,
            "BUYER"
        );

        assertTrue(cardInfo.equals(cardInfo));
        assertTrue(cardInfo.equals(sameCardInfo));
        assertTrue(!cardInfo.equals(otherCardInfo));
        assertTrue(!cardInfo.equals(otherCardInfoB));
        assertFalse("1".equals(cardInfo));
    }

    @Test
    @DisplayName("hashcode를 재정의 하면 값으로 객체를 비교한다.")
    void hashcode_test() {
        CardInfo cardInfo = createCardInfo();
        CardInfo sameCardInfo = createCardInfo();
        CardInfo otherCardInfo = new CardInfo(
            new BigDecimal(130_000L),
            "24",
            "24",
            "14345*123409*01234",
            "0",
            "000000",
            Boolean.FALSE,
            "신용",
            "개인",
            "COMPLETED",
            Boolean.FALSE,
            "BUYER"
        );
        assertTrue(cardInfo.hashCode() == sameCardInfo.hashCode());
        assertFalse(cardInfo.hashCode() == otherCardInfo.hashCode());
    }

    @Test
    @DisplayName("toString을 재정의 하면 지정된 양식으로 출력된다.")
    void to_string_override_test() {
        CardInfo cardInfo = createCardInfo();
        String expected = String.format(
            "cardNumber: %s, cardType: %s, cardIssuerCompany: %s, cardAcquirerCompany: %s",
            cardInfo.getCardNumber(),
            cardInfo.getCardType(),
            cardInfo.getCardIssuerCompany(),
            cardInfo.getCardAcquirerCompany()
        );
        assertEquals(expected, cardInfo.toString());
    }
}
