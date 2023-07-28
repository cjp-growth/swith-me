package project.swithme.order.core.web.payment.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.swithme.order.core.domain.payment.entity.toss.Toss;
import project.swithme.order.core.domain.payment.entity.toss.pojo.Payment;
import project.swithme.order.core.domain.payment.persistence.toss.TossJpaRepository;
import project.swithme.order.core.web.payment.application.PaymentSaveUseCase;

@Service
@RequiredArgsConstructor
public class PaymentSaveService implements PaymentSaveUseCase {

    private final TossJpaRepository tossRepository;

    @Override
    public Long save(Payment payment) {
        Toss toss = new Toss(
            payment.getUserId(),
            payment.getOrderId(),
            payment.getPaymentKey(),
            payment.getPaymentType(),
            payment.getPrice()
        );

        Toss savedToss = tossRepository.save(toss);
        return savedToss.getId();
    }
}
