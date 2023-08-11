package order;

import java.math.BigDecimal;
import java.time.Instant;
import project.swithme.domain.core.common.BaseInformation;
import project.swithme.domain.core.payment.entity.CardInfo;
import project.swithme.domain.core.payment.entity.Payment;
import project.swithme.domain.core.payment.entity.PaymentMethod;
import project.swithme.domain.core.payment.entity.PaymentStatus;
import project.swithme.domain.core.payment.entity.PaymentType;

public final class PaymentFixture {

    public static final BigDecimal FIXED_TOTAL_PRICE = new BigDecimal(130_000);
    public static final String FIXED_TOTAL_PRICE_PARAM = FIXED_TOTAL_PRICE.toString();
    private static final String PAYMENT_KEY = "toss-payment-key";

    private PaymentFixture() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public static String getOrderUniqueId() {
        return "f453ab17-3796-11ee-ad7d-bbd883b46e6d";
    }

    public static String getPaymentKey() {
        return "lqNRYD097kZLKGPx4M3M1RXWYWg9ve3BaWypv1o6bemnOJz2";
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
            .method(PaymentMethod.CARD)
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
            .method(PaymentMethod.CARD)
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
