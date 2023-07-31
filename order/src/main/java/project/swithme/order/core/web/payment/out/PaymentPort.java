package project.swithme.order.core.web.payment.out;

import java.math.BigDecimal;
import project.swithme.order.core.domain.payment.entity.command.TossPaymentCommand;

public interface PaymentPort {

    TossPaymentCommand requestApproval(String paymentKey, String orderId, BigDecimal amount);
}
