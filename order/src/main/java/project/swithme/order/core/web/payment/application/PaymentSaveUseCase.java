package project.swithme.order.core.web.payment.application;

import project.swithme.order.core.domain.payment.entity.Payment;

public interface PaymentSaveUseCase {

    Long save(Payment payment);
}
