package project.swithme.order.common.testhelper.payment.fixture;

import java.math.BigDecimal;
import java.time.Instant;
import project.swithme.order.core.common.BaseInformation;
import project.swithme.order.core.domain.payment.entity.CardInfo;
import project.swithme.order.core.domain.payment.entity.Method;
import project.swithme.order.core.domain.payment.entity.Payment;
import project.swithme.order.core.domain.payment.entity.PaymentStatus;
import project.swithme.order.core.domain.payment.entity.PaymentType;
import project.swithme.order.core.domain.payment.entity.command.PaymentCommand;

public final class PaymentFixture {

    public static final BigDecimal FIXED_TOTAL_PRICE = new BigDecimal(130_000);
    public static final String FIXED_TOTAL_PRICE_PARAM = FIXED_TOTAL_PRICE.toString();
    private static final String PAYMENT_KEY = "toss-payment-key";

    private PaymentFixture() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public static String getPaymentKey() {
        return "lqNRYD097kZLKGPx4M3M1RXWYWg9ve3BaWypv1o6bemnOJz2";
    }

    public static PaymentCommand createApprovedCommand() {
        Payment payment = createPayment(1L);
        return new PaymentCommand(
            payment.getVersion(),
            getPaymentKey(),
            payment.getPaymentType().name(),
            payment.getOrderId().toString(),
            payment.getOrderName(),
            payment.getMId(),
            payment.getCurrency(),
            "카드",
            payment.getTotalAmount(),
            payment.getBalanceAmount(),
            payment.getPaymentStatus().name(),
            payment.getRequestedAt(),
            payment.getApprovedAt(),
            payment.getUseEscrow(),
            payment.getLastTransactionKey(),
            payment.getSuppliedAmount(),
            payment.getVat(),
            payment.getCultureExpense(),
            payment.getTaxFreeAmount(),
            payment.getTaxExemptionAmount(),
            null,
            payment.getCardInfo(),
            null,
            payment.getSecret(),
            null,
            null,
            null,
            null,
            null,
            "KR",
            null
        );
    }

    public static PaymentCommand createNotApprovedCommand() {
        Payment payment = createPayment(1L);
        return new PaymentCommand(
            payment.getVersion(),
            getPaymentKey(),
            payment.getPaymentType().name(),
            payment.getOrderId().toString(),
            payment.getOrderName(),
            payment.getMId(),
            payment.getCurrency(),
            "카드",
            payment.getTotalAmount(),
            payment.getBalanceAmount(),
            payment.getPaymentStatus().name(),
            payment.getRequestedAt(),
            null,
            payment.getUseEscrow(),
            payment.getLastTransactionKey(),
            payment.getSuppliedAmount(),
            payment.getVat(),
            payment.getCultureExpense(),
            payment.getTaxFreeAmount(),
            payment.getTaxExemptionAmount(),
            null,
            payment.getCardInfo(),
            null,
            payment.getSecret(),
            null,
            null,
            null,
            null,
            null,
            "KR",
            null
        );
    }

    public static Payment createPayment(
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
        return Payment.builder()
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

    public static Payment createPayment(Long id) {
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
        return Payment.builder()
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
