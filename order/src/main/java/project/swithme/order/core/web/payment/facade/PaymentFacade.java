package project.swithme.order.core.web.payment.facade;

import static project.swithme.order.core.domain.order.entity.OrderStatus.COMPLETE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.swithme.order.core.domain.order.entity.Order;
import project.swithme.order.core.domain.payment.entity.Payment;
import project.swithme.order.core.web.order.application.OrderQueryUseCase;
import project.swithme.order.core.web.order.exception.OrderNotFoundException;
import project.swithme.order.core.web.payment.application.PaymentSaveUseCase;
import project.swithme.order.core.web.payment.facade.validator.PaymentValidator;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final OrderQueryUseCase orderQueryUseCase;
    private final PaymentValidator paymentValidator;
    private final PaymentSaveUseCase paymentSaveUseCase;

    @Transactional
    public Long pay(
        String orderUniqueId,
        Payment payment
    ) {
        Order findOrder = orderQueryUseCase.findOrderByUniqueId(orderUniqueId)
            .orElseThrow(OrderNotFoundException::new);

        paymentValidator.validate(findOrder, payment);

        findOrder.updateOrderStatus(COMPLETE);
        return paymentSaveUseCase.save(payment);
    }
}
