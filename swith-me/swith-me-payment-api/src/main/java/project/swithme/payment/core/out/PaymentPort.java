package project.swithme.payment.core.out;

import java.math.BigDecimal;
import project.swithme.domain.core.payment.entity.command.PaymentCommand;

public interface PaymentPort {

    PaymentCommand requestApproval(String paymentKey, String orderId, BigDecimal amount);
}
