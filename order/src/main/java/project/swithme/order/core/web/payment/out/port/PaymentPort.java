package project.swithme.order.core.web.payment.out.port;

import java.math.BigDecimal;
import project.swithme.order.core.domain.payment.entity.command.PaymentCommand;

public interface PaymentPort {

    PaymentCommand requestApproval(String paymentKey, String orderId, BigDecimal amount);
}
