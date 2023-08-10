package project.swithme.payment.core.application;

import project.swithme.domain.core.payment.entity.Payment;
import project.swithme.domain.core.payment.entity.PaymentType;
import project.swithme.domain.core.payment.entity.command.PaymentCommand;

public interface PaymentSaveUseCase {

    Payment save(Long userId, Long orderId, PaymentType paymentType, PaymentCommand command);
}
