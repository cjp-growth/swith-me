package project.swithme.order.core.web.payment.application;

import project.swithme.order.core.domain.payment.entity.PaymentType;
import project.swithme.order.core.domain.payment.entity.Toss;
import project.swithme.order.core.domain.payment.entity.command.TossPaymentCommand;

public interface PaymentSaveUseCase {

    Toss save(Long userId, Long orderId, PaymentType paymentType, TossPaymentCommand command);
}
