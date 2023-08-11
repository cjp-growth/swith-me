package project.swithme.payment.core.out;

import java.math.BigDecimal;
import project.swithme.payment.core.facade.command.PaymentCommand;

public interface PaymentPort {

    PaymentCommand requestApproval(String paymentKey, String orderId, BigDecimal amount);
}
