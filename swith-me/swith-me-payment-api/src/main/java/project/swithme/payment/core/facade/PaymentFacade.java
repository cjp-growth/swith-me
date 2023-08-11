package project.swithme.payment.core.facade;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.swithme.domain.core.order.entity.Order;
import project.swithme.domain.core.payment.entity.Payment;
import project.swithme.domain.core.payment.entity.PaymentType;
import project.swithme.domain.core.payment.entity.command.PaymentCommand;
import project.swithme.payment.core.application.PaymentSaveUseCase;
import project.swithme.payment.core.exception.PaymentFailureException;
import project.swithme.payment.core.facade.validator.PaymentValidator;
import project.swithme.payment.core.out.port.PaymentPort;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    //private final OrderQueryUseCase orderQueryUseCase;
    private final PaymentValidator paymentValidator;
    private final PaymentSaveUseCase paymentSaveUseCase;
    private final PaymentPort paymentPort;

    @Transactional
    public Long requestApproval(
        String orderUniqueId,
        String paymentKey,
        PaymentType paymentType,
        BigDecimal amount
    ) {
        // Order findOrder = orderQueryUseCase.findByUniqueId(orderUniqueId)
        // .orElseThrow(OrderNotFoundException::new);

        // paymentValidator.validate(null, amount);

        PaymentCommand command = paymentPort.requestApproval(paymentKey, orderUniqueId, amount);
        if (!command.isApproved()) {
            throw new PaymentFailureException();
        }
        Payment paymentPayment = save(paymentType, null, command);
        // findOrder.updateOrderStatus(COMPLETE);
        // findOrder.updatePrice(paymentPayment.getDiscountedAmount());
        return paymentPayment.getId();
    }

    private Payment save(
        PaymentType paymentType,
        Order findOrder,
        PaymentCommand paymentInfo
    ) {
        return paymentSaveUseCase.save(
            findOrder.getUserId(),
            findOrder.getId(),
            paymentType,
            paymentInfo
        );
    }
}
