package project.swithme.order.common.testhelper.payment.fixture;

import static project.swithme.order.common.testhelper.env.TossPaymentFixture.getPaymentKey;
import java.math.BigDecimal;
import java.time.Instant;
import project.swithme.order.core.common.BaseInformation;
import project.swithme.order.core.domain.payment.entity.CardInfo;
import project.swithme.order.core.domain.payment.entity.Method;
import project.swithme.order.core.domain.payment.entity.PaymentStatus;
import project.swithme.order.core.domain.payment.entity.PaymentType;
import project.swithme.order.core.domain.payment.entity.Toss;
import project.swithme.order.core.domain.payment.entity.command.TossPaymentCommand;

public final class TossFixture {

    private TossFixture() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public static TossPaymentCommand createApprovedCommand() {
        Toss toss = createToss(1L);
        return new TossPaymentCommand(
            toss.getVersion(),
            getPaymentKey(),
            toss.getPaymentType().name(),
            toss.getOrderId().toString(),
            toss.getOrderName(),
            toss.getMId(),
            toss.getCurrency(),
            "카드",
            toss.getTotalAmount(),
            toss.getBalanceAmount(),
            toss.getPaymentStatus().name(),
            toss.getRequestedAt(),
            toss.getApprovedAt(),
            toss.getUseEscrow(),
            toss.getLastTransactionKey(),
            toss.getSuppliedAmount(),
            toss.getVat(),
            toss.getCultureExpense(),
            toss.getTaxFreeAmount(),
            toss.getTaxExemptionAmount(),
            null,
            toss.getCardInfo(),
            null,
            toss.getSecret(),
            null,
            null,
            null,
            null,
            null,
            "KR",
            null
        );
    }

    public static TossPaymentCommand createNotApprovedCommand() {
        Toss toss = createToss(1L);
        return new TossPaymentCommand(
            toss.getVersion(),
            getPaymentKey(),
            toss.getPaymentType().name(),
            toss.getOrderId().toString(),
            toss.getOrderName(),
            toss.getMId(),
            toss.getCurrency(),
            "카드",
            toss.getTotalAmount(),
            toss.getBalanceAmount(),
            toss.getPaymentStatus().name(),
            toss.getRequestedAt(),
            null,
            toss.getUseEscrow(),
            toss.getLastTransactionKey(),
            toss.getSuppliedAmount(),
            toss.getVat(),
            toss.getCultureExpense(),
            toss.getTaxFreeAmount(),
            toss.getTaxExemptionAmount(),
            null,
            toss.getCardInfo(),
            null,
            toss.getSecret(),
            null,
            null,
            null,
            null,
            null,
            "KR",
            null
        );
    }

    public static Toss createToss(
        Long id,
        Long orderId
    ) {
        CardInfo cardInfo = new CardInfo(
            new BigDecimal(130_000L),
            "24",
            "24",
            "01234*123409*01234",
            "0",
            "000000",
            Boolean.FALSE,
            "신용",
            "개인",
            "COMPLETED",
            Boolean.FALSE,
            "BUYER"
        );
        return Toss.builder()
            .id(id)
            .orderId(orderId)
            .paymentKey("abcde-aabcd")
            .paymentType(PaymentType.NORMAL)
            .mId(null)
            .version("2022-10-10")
            .orderName("스터디카페 및 락커 1달 정기 이용권")
            .currency("KRW")
            .method(Method.CARD)
            .totalAmount(new BigDecimal(130_000L))
            .balanceAmount(new BigDecimal(130_000L))
            .suppliedAmount(new BigDecimal(118_300L))
            .vat(new BigDecimal(100L))

            .taxExemptionAmount(BigDecimal.ZERO)
            .taxFreeAmount(BigDecimal.ZERO)

            .paymentStatus(PaymentStatus.DONE)
            .requestedAt(Instant.now())
            .approvedAt(Instant.now().plusSeconds(1_000L))
            .useEscrow(false)
            .cultureExpense(false)
            .cardInfo(cardInfo)
            .virtualAccountInfo(null)
            .secret(null)
            .mobileInfo(null)
            .giftCertificateInfo(null)
            .transferInfo(null)
            .receiptUrl(null)
            .easyPayInfo(null)
            .country("KR")
            .baseInformation(new BaseInformation(1L))
            .build();
    }

    public static Toss createToss(Long id) {
        CardInfo cardInfo = new CardInfo(
            new BigDecimal(130_000L),
            "24",
            "24",
            "01234*123409*01234",
            "0",
            "000000",
            Boolean.FALSE,
            "신용",
            "개인",
            "COMPLETED",
            Boolean.FALSE,
            "BUYER"
        );
        return Toss.builder()
            .orderId(id)
            .paymentKey("abcde-aabcd")
            .paymentType(PaymentType.NORMAL)
            .mId(null)
            .version("2022-10-10")
            .orderName("스터디카페 및 락커 1달 정기 이용권")
            .currency("KRW")
            .method(Method.CARD)
            .totalAmount(new BigDecimal(130_000L))
            .balanceAmount(new BigDecimal(130_000L))
            .suppliedAmount(new BigDecimal(118_300L))
            .vat(new BigDecimal(100L))

            .taxExemptionAmount(BigDecimal.ZERO)
            .taxFreeAmount(BigDecimal.ZERO)

            .paymentStatus(PaymentStatus.DONE)
            .requestedAt(Instant.now())
            .approvedAt(Instant.now().plusSeconds(1_000L))
            .useEscrow(false)
            .cultureExpense(false)
            .cardInfo(cardInfo)
            .virtualAccountInfo(null)
            .secret(null)
            .mobileInfo(null)
            .giftCertificateInfo(null)
            .transferInfo(null)
            .receiptUrl(null)
            .easyPayInfo(null)
            .country("KR")
            .baseInformation(new BaseInformation(1L))
            .build();
    }
}
