package project.swithme.order.common.testhelper.payment.fixture;

import com.fasterxml.uuid.Generators;
import java.math.BigDecimal;
import java.util.UUID;

public final class PaymentFixture {

    public static final BigDecimal FIXED_TOTAL_PRICE = new BigDecimal(130_000);
    public static final String FIXED_TOTAL_PRICE_PARAM = FIXED_TOTAL_PRICE.toString();

    private static final String PAYMENT_KEY = "toss-payment-key";

    private PaymentFixture() {
        throw new AssertionError("올바른 방식으로 생성자를 호출해주세요.");
    }

    public static String createOrderUniqueId() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        return uuid.toString();
    }
}
