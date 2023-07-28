package project.swithme.order.common.testhelper.payment.fixture;

import com.fasterxml.uuid.Generators;
import java.math.BigDecimal;
import java.util.UUID;
import project.swithme.order.core.domain.payment.entity.Payment;
import project.swithme.order.core.domain.payment.entity.toss.PaymentType;
import project.swithme.order.core.domain.payment.entity.toss.pojo.payments.TossPayment;

public final class PaymentFixture {

    private static final String PAYMENT_KEY = "toss-payment-key";

    private PaymentFixture() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public static String createOrderUniqueId() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        return uuid.toString();
    }

    public static Payment createTossPayment(String uuid) {
        BigDecimal totalPrice = new BigDecimal(100_000L);
        return new TossPayment(uuid, PAYMENT_KEY, PaymentType.NORMAL, totalPrice);
    }

    public static Payment createTossPayment(
        String uuid,
        BigDecimal price
    ) {
        return new TossPayment(uuid, PAYMENT_KEY, PaymentType.NORMAL, price);
    }
}
