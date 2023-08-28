package project.swithme.payment.core.facade;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.swithme.domain.core.order.event.PaymentSuccessEvent;
import project.swithme.domain.core.payment.entity.Payment;
import project.swithme.domain.core.payment.entity.PaymentType;
import project.swithme.payment.common.command.OrderValidationCommand;
import project.swithme.payment.core.application.PaymentSaveUseCase;
import project.swithme.payment.core.exception.PaymentFailureException;
import project.swithme.payment.core.facade.command.PaymentCommand;
import project.swithme.payment.core.facade.validator.PaymentValidator;
import project.swithme.payment.core.out.EventPublisher;
import project.swithme.payment.core.out.OrderQueryPort;
import project.swithme.payment.core.out.PaymentPort;

@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final OrderQueryPort orderQueryPort;
    private final PaymentValidator paymentValidator;
    private final PaymentSaveUseCase paymentSaveUseCase;
    private final PaymentPort paymentPort;
    private final EventPublisher<PaymentSuccessEvent> eventPublisher;

    @Transactional
    public Long requestApproval(
        String orderUniqueId,
        String paymentKey,
        PaymentType paymentType,
        BigDecimal amount
    ) {
        OrderValidationCommand findOrderCommand = orderQueryPort.findOrderByUniqueId(orderUniqueId);
        paymentValidator.validate(findOrderCommand, amount);

        PaymentCommand paymentCommand = paymentPort.requestApproval(
            paymentKey,
            orderUniqueId,
            amount
        );
        if (!paymentCommand.isApproved()) {
            throw new PaymentFailureException();
        }

        Payment newPayment = paymentSaveUseCase.save(
            findOrderCommand.getUserId(),
            findOrderCommand.getOrderId(),
            paymentType,
            paymentCommand
        );

        pushNotification(findOrderCommand);
        return newPayment.getId();
    }

    private void pushNotification(OrderValidationCommand findOrderCommand) {
        PaymentSuccessEvent event = new PaymentSuccessEvent(
            findOrderCommand.getOrderUniqueIdAsString(),
            findOrderCommand.getUserId()
        );
        eventPublisher.publishEvent(event);
    }
}
