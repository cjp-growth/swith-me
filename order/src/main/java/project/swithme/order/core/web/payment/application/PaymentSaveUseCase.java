package project.swithme.order.core.web.payment.application;

import project.swithme.order.core.domain.payment.entity.Payment;
import project.swithme.order.core.domain.payment.entity.PaymentType;
import project.swithme.order.core.domain.payment.entity.command.PaymentCommand;

public interface PaymentSaveUseCase {

    Payment save(Long userId, Long orderId, PaymentType paymentType, PaymentCommand command);
}
